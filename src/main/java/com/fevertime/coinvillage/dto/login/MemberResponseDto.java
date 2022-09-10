package com.fevertime.coinvillage.dto.login;

import com.fevertime.coinvillage.domain.Job;
import com.fevertime.coinvillage.domain.member.Member;
import com.fevertime.coinvillage.dto.job.JobResponseDto;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {
    private Long memberId;

    private String email;

    private String password;

    private String nickname;

    private String phoneNumber;

    private Long property;

    private String countryName;

    private Set<AuthorityDto> authorityDtoSet;

    private String jobName;

    private String jobContent;

    private long payCheck;

    public MemberResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        this.phoneNumber = member.getPhoneNumber();
        this.property = member.getProperty();
        this.countryName = member.getCountry().getCountryName();
        this.authorityDtoSet = member.getAuthorities().stream()
                .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                .collect(Collectors.toSet());
        if (member.getJob().getJobName() == null) {
            this.jobName = "무직";
        } else {
            this.jobName = member.getJob().getJobName();
        }
        if (member.getJob().getJobContent() == null) {
            this.jobName = "없음";
        } else {
            this.jobContent = member.getJob().getJobContent();
        }
        if (member.getJob().getPayCheck() == null) {
            this.payCheck = 0L;
        } else {
            this.payCheck = member.getJob().getPayCheck();
        }
    }
}
