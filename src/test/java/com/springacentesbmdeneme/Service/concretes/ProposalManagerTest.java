package com.springacentesbmdeneme.Service.concretes;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.springacentesbmdeneme.Repository.abstracts.ProposalJpa;
import com.springacentesbmdeneme.entites.Proposal;



@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
class ProposalManagerTest {
	

    @Autowired
    private ProposalManager proposalService;
    @Autowired
    private ProposalJpa proposalJpa;
    @Mock
    private Proposal proposal;
    @BeforeEach
    void init() {
    	proposal=new Proposal().builder()
    			.order_date(new Date())
    			.accepted_date(new Date())
    			.price(100.00)
    			.vehicle(new ArrayList<>())
    			.status("waiting")
    			.build();
		
		proposalService.save(proposal);
    }
	@Test
	public void proposalGetByIdMethod_Test() {
		Proposal proposalTemp=proposalService.getProposalById(proposal.getId());
		Assertions.assertThat(proposalTemp.getPrice()).isEqualTo(proposal.getPrice());
	}
    @Test
    public void proposalSaveMethod_Test() {
    	
    	Assertions.assertThat(proposal.getId()).isGreaterThan(0L);
    }
    @Test
    public void proposalDeclineMethod_Test() {
    	proposalService.decline(proposal);
    	Assertions.assertThat(proposal.getStatus()).isEqualTo("declined");
    }
    @Test
    public void proposalCanceledMethod_Test() {
    	proposalService.cancel(proposal);
    	Assertions.assertThat(proposal.getStatus()).isEqualTo("canceled");
    	Assertions.assertThat(proposal.getEnd_date()).isBefore(new Date());
    }
    @Test
    public void proposalAcceptedMethod_Test() {
    	proposalService.proposalAccept(proposal);
    	Assertions.assertThat(proposal.getStatus()).isEqualTo("accepted");
    	Assertions.assertThat(proposal.getEnd_date()).isWithinYear(2023);
    }
 
    @Test
    public void   WaitingProposalsDeclineMethod_Test() {
    	Proposal proposal1=new Proposal().builder()
    			.order_date(new Date())
    			.accepted_date(new Date())
    			.price(100.00)
    			.vehicle(new ArrayList<>())
    			
    			.build();
    	List<Proposal> proposalList=new ArrayList<Proposal>() {
    		{
    			add(proposal);
    			add(proposal1);    			
    		}
    	};
    	proposalService.  WaitingProposalsDecline(proposalList);
    	Assertions.assertThat(proposal1.getStatus()).isEqualTo("declined");
    	
    }
}
   
