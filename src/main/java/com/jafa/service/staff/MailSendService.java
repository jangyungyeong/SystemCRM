package com.jafa.service.staff;

import java.security.SecureRandom;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.repository.staff.StaffRepository;

@Service
public class MailSendService {

	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private int authNumber;
	
	// 가입인증번호
	public void makeRandomNumber() {
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		authNumber = checkNum;
		System.out.println("인증번호 : " + authNumber);
	}
	
	// 임시비밀번호 생성
	public String generrateTempPassword() {
		StringBuilder charSb = new StringBuilder();
		for (char c = 48; c <= 122; c++) {
			if (c>=58 && c<=64 || c>=91 && c<=96) {
				continue;
			}
			charSb.append(c);
		}
		String characters = charSb.toString();
		Random random2 = new SecureRandom();
		
		StringBuilder sb = new StringBuilder(12);
		for (int i = 0; i < 12; i++) {
			int randomIdx = random2.nextInt(characters.length());
			char randomChar = characters.charAt(randomIdx);
			sb.append(randomChar);
		}
		return sb.toString();
	}
	
	// 회원가입 인증 메일 양식
	public String joinEmail(String email) {
		makeRandomNumber();
		String setFrom = "dbsrud77777@naver.com";
		String toMail = email;
		String title = "[CRM]회원가입 인증 이메일 입니다.";
		String content = "회원가입을 위한 인증번호는 " + authNumber + "입니다. " + "<br>" + 
				"해당 인증번호를 인증번호 확인란에 기입해주세요.";
		mailSend(setFrom, toMail, title, content);
		return Integer.toString(authNumber);
	}

	// 아이디 찾기 메일 양식
	public void findIdByEmail(String email) {
		
		String findStaffId = staffRepository.selectByEmail(email);
		if (findStaffId==null) {
			throw new NotFoundMemberException();
		}
		
		String setFrom = "dbsrud77777@naver.com";
		String toMail = email;
		String title = "[CRM]아이디 찾기 메일 서비스 입니다.";
		String content = "회원님의 아이디는 <b>" + findStaffId + "입니다. ";
		mailSend(setFrom, toMail, title, content);
	}
	
	// 임시비밀번호 메일 전송
	@Transactional
	public void findPwdByEmail(String email) {
		String findMemberId = staffRepository.selectByEmail(email);
		if (findMemberId==null) {
			throw new NotFoundMemberException();
		}
		String tempPassword = generrateTempPassword();
		staffRepository.updatePassword(findMemberId, passwordEncoder.encode(tempPassword));
		
		String setFrom = "dbsrud77777@naver.com";
		String toMail = email;
		String title = "[CRM]임시비밀번호 발급 메일 서비스 입니다."; 
		String content = "회원님의 비밀번호는 <b> " + tempPassword + "입니다."; 
		mailSend(setFrom, toMail, title, content);
	}
	
	// 이메일 전송 메소드
	private void mailSend(String setFrom, String toMail, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content,true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
