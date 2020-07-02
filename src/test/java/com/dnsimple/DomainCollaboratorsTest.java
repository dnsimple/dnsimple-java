package com.dnsimple;

import com.dnsimple.data.Collaborator;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.response.PaginatedResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.dnsimple.tools.CustomMatchers.thrownException;
import static com.dnsimple.endpoints.http.HttpMethod.DELETE;
import static com.dnsimple.endpoints.http.HttpMethod.GET;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainCollaboratorsTest extends DnsimpleTestBase {
    @Test
    public void testListCollaboratorsSupportsPagination() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listCollaborators("1", "example.com", singletonMap("page", 1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/collaborators?page=1"));
    }

    @Test
    public void testListCollaboratorsSupportsExtraRequestOptions() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listCollaborators("1", "example.com", singletonMap("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/collaborators?foo=bar"));
    }

    @Test
    public void testCollaboratorsSupportsSorting() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listCollaborators("1", "example.com", singletonMap("sort", "created_at:asc"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/collaborators?sort=created_at%3Aasc"));
    }

    @Test
    public void testCollaboratorsProducesDelegationSignerRecordList() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listCollaborators/success.http");
        List<Collaborator> collaborators = client.domains.listCollaborators("1", "example.com").getData();
        assertThat(collaborators, hasSize(2));
        assertThat(collaborators.get(0).getId(), is(100));
    }

    @Test
    public void testListCollaboratorsExposesPaginationInfo() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listCollaborators/success.http");
        PaginatedResponse<Collaborator> response = client.domains.listCollaborators("1", "example.com");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testAddColaboratorProducersInvitedUserCollaborator() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("addCollaborator/invite-success.http");
        Collaborator collaborator = client.domains.addCollaborator("1", "example.com", singletonMap("email", "invited-user@example.com")).getData();
        assertThat(collaborator.getId(), is(101));
        assertThat(collaborator.getDomainId(), is(1));
        assertThat(collaborator.getDomainName(), is("example.com"));
        assertThat(collaborator.getUserId(), is(nullValue()));
        assertThat(collaborator.getUserEmail(), is("invited-user@example.com"));
        assertThat(collaborator.getInvitation(), is(true));
    }

    @Test
    public void testAddColaboratorProducersExistingUserCollaborator() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("addCollaborator/success.http");
        Collaborator collaborator = client.domains.addCollaborator("1", "example.com", singletonMap("email", "invited-user@example.com")).getData();
        assertThat(collaborator.getId(), is(100));
        assertThat(collaborator.getDomainId(), is(1));
        assertThat(collaborator.getDomainName(), is("example.com"));
        assertThat(collaborator.getUserId(), is(999));
        assertThat(collaborator.getUserEmail(), is("existing-user@example.com"));
        assertThat(collaborator.getInvitation(), is(false));
    }

    @Test
    public void testRemoveCollaborator() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("removeCollaborator/success.http");
        client.domains.removeCollaborator("1", "example.com", "100");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/collaborators/100"));
    }

    @Test
    public void testRemovecollaboratorWhenNotFound() {
        server.stubFixtureAt("notfound-collaborator.http");
        assertThat(() -> client.domains.removeCollaborator("1", "example.com", "0"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }
}
