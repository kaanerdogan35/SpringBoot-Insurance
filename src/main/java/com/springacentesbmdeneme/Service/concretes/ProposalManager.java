package com.springacentesbmdeneme.Service.concretes;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.ProposalJpa;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.entites.Proposal;
@Service
public class ProposalManager implements ProposalService {

		@Autowired
		private ProposalJpa proposalDao;

		@Override
		public List<Long> getProposalsIdByVehicleId(Long id) {
			
			return this.proposalDao.findProposalsByVehicleId(id);
		}
		@Override
		public List<Long> getProposalsIdByDaskId(Long id) {
			
			return this.proposalDao.findProposalsByDaskId(id);
		}
		@Override
		public boolean checkProposalStatusAcceptedorWaiting(List<Long> id) {
			
			for(Long proposal :id) {
				if(this.proposalDao.checkAcceptedorWaiting(proposal).toString()=="0") {
					return true;
				}
			}	
			return false;
		}

		@Override
		public void save(Proposal proposal) {
			this.proposalDao.save(proposal);
			
		}

		@Override
		public void WaitingProposalsDecline(List<Proposal> proposals) {
			for(Proposal proposal:proposals) {
				if(proposal.getStatus()==null) {
					proposal.setStatus("declined");
					this.save(proposal);
				}
			}
			
		}

		@Override
		public List<Proposal> getAll() {
			// TODO Auto-generated method stub
			return this.proposalDao.findAll();
		}

		@Override
		public Proposal getProposalById(Long id) {
			// TODO Auto-generated method stub
			return this.proposalDao.findById(id).get();
		}
		@Override
		public void proposalAccept(Proposal proposal) {
			Calendar cal = Calendar.getInstance();
			Date date =new Date();
			proposal.setAccepted_date(date);
			cal.add(Calendar.YEAR, 1);
			date=cal.getTime();
			proposal.setEnd_date(date);
			proposal.setStatus("accepted");
			this.proposalDao.save(proposal);
		}

		@Override
		public void decline(Proposal proposal) {
			proposal.setStatus("declined");
			this.proposalDao.save(proposal);
		}
		@Override
		public void cancel(Proposal proposal) {
			proposal.setStatus("canceled");
			proposal.setEnd_date(new Date());
			this.proposalDao.save(proposal);
		}

		
}
