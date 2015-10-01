package sku.microblog.business.service;

import java.util.List;
import java.util.Map;

import sku.microblog.business.domain.Member;
import sku.microblog.dataaccess.MemberDaoImpl;
import sku.microblog.util.DataDuplicatedException;
import sku.microblog.util.DataNotFoundException;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao memberDataAccess;

	public MemberServiceImpl() {
		memberDataAccess = new MemberDaoImpl();
	}
	
	@Override
	public Member loginCheck(String email, String password) {
		return memberDataAccess.checkMember(email, password);
	}

	@Override
	public void registerMember(Member member) throws DataDuplicatedException {
		if(memberDataAccess.memberEmailExists(member.getEmail())){
			throw new DataDuplicatedException("registerMember Error");
		} else {
			memberDataAccess.insertMember(member);
		}
	}

	@Override
	public void updateMember(Member member) throws DataNotFoundException {
		if(memberDataAccess.memberEmailExists(member.getEmail())){
			memberDataAccess.updateMember(member);
		} else {
			throw new DataNotFoundException("updateMember Error");
		}
	}

	@Override
	public void removeMember(Member member) throws DataNotFoundException {
		if(memberDataAccess.memberEmailExists(member.getEmail())){
			memberDataAccess.deleteMember(member);
		} else {
			throw new DataNotFoundException("removeMember Error");
		}
	}

	@Override
	public Member findMember(String name) throws DataNotFoundException {
		if(memberDataAccess.memberNameExists(name)){
			return memberDataAccess.selectMemberAsName(name);
		} else {
			throw new DataNotFoundException("findMember Error");
		}
	}

	@Override
	public Member[] getAllUsers() {
		return memberDataAccess.selectAllMembers();
	}

	@Override
	public List<Member> getMemberList(Map<String, Object> searchInfo) {
		return memberDataAccess.selectMemberList(searchInfo);
	}

	@Override
	public int getMemberCount(Map<String, Object> searchInfo) {
		return memberDataAccess.selectMemberCount(searchInfo);
	}

	@Override
	public boolean availableName(String name) {
		if(memberDataAccess.memberNameExists(name)){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void giveRole(Member administrator, String targetMemberName, int role)
			throws DataNotFoundException {
		// TODO Auto-generated method stub

	}

}
