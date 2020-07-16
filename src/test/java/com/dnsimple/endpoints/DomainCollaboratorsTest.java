package com.dnsimple.endpoints;

import com.dnsimple.data.Collaborator;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static com.dnsimple.http.HttpMethod.DELETE;
import static com.dnsimple.http.HttpMethod.GET;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static java.time.ZoneOffset.UTC;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainCollaboratorsTest extends DnsimpleTestBase {
    @Test
    public void testListCollaboratorsSupportsPagination() {
        client.domains.listCollaborators(1, "example.com", new ListOptions.Builder().page(1).build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/collaborators?page=1"));
    }

    @Test
    public void testListCollaboratorsSupportsExtraRequestOptions() {
        client.domains.listCollaborators(1, "example.com", new ListOptions.Builder().filter("foo", "bar").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/collaborators?foo=bar"));
    }

    @Test
    public void testCollaboratorsSupportsSorting() {
        client.domains.listCollaborators(1, "example.com", new ListOptions.Builder().sortAsc("created_at").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/collaborators?sort=created_at%3Aasc"));
    }

    @Test
    public void testCollaboratorsProducesCollaboratorList() {
        server.stubFixtureAt("listCollaborators/success.http");
        List<Collaborator> collaborators = client.domains.listCollaborators(1, "example.com").getData();
        assertThat(collaborators, hasSize(2));
        assertThat(collaborators.get(0).getId(), is(100L));
    }

    @Test
    public void testListCollaboratorsExposesPaginationInfo() {
        server.stubFixtureAt("listCollaborators/success.http");
        PaginatedResponse<Collaborator> response = client.domains.listCollaborators(1, "example.com");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testAddColaboratorProducersInvitedUserCollaborator() {
        server.stubFixtureAt("addCollaborator/invite-success.http");
        Collaborator collaborator = client.domains.addCollaborator(1, "example.com", singletonMap("email", "invited-user@example.com")).getData();
        assertThat(collaborator.getId(), is(101L));
        assertThat(collaborator.getDomainId(), is(1L));
        assertThat(collaborator.getDomainName(), is("example.com"));
        assertThat(collaborator.getUserId(), is(nullValue()));
        assertThat(collaborator.getUserEmail(), is("invited-user@example.com"));
        assertThat(collaborator.hasInvitation(), is(true));
    }

    @Test
    public void testAddColaboratorProducersExistingUserCollaborator() {
        server.stubFixtureAt("addCollaborator/success.http");
        Collaborator collaborator = client.domains.addCollaborator(1, "example.com", singletonMap("email", "invited-user@example.com")).getData();
        assertThat(collaborator.getId(), is(100L));
        assertThat(collaborator.getDomainId(), is(1L));
        assertThat(collaborator.getDomainName(), is("example.com"));
        assertThat(collaborator.getUserId(), is(999L));
        assertThat(collaborator.getUserEmail(), is("existing-user@example.com"));
        assertThat(collaborator.hasInvitation(), is(false));
        assertThat(collaborator.getCreatedAt(), is(OffsetDateTime.of(2016, 10, 7, 8, 53, 41, 0, UTC)));
        assertThat(collaborator.getUpdatedAt(), is(OffsetDateTime.of(2016, 10, 7, 8, 53, 41, 0, UTC)));
        assertThat(collaborator.getAcceptedAt(), is(OffsetDateTime.of(2016, 10, 7, 8, 53, 41, 0, UTC)));
    }

    @Test
    public void testRemoveCollaborator() {
        server.stubFixtureAt("removeCollaborator/success.http");
        client.domains.removeCollaborator(1, "example.com", "100");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/collaborators/100"));
    }

    @Test
    public void testRemovecollaboratorWhenNotFound() {
        server.stubFixtureAt("notfound-collaborator.http");
        assertThat(() -> client.domains.removeCollaborator(1, "example.com", "0"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }
}
