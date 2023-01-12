create table qnaBoard(
   	num number,
   	writer varchar2(20),
 	email varchar2(30),
	subject varchar2(50),
	reg_date date,
	readcount number default 0, 
	ref number, 
	re_step number, 
	re_level number, 
	content varchar2(100),
	ip varchar2(20),
    upload varchar2(300)
);

create sequence qnaBoard_num_seq
start with 1 
increment by 1
nocache
nocycle;

insert into qnaBoard 
values(qnaBoard_num_seq.nextval, '홍길동','young@aaaa.com','제목1',sysdate,0,qnaBoard_num_seq.nextval,
0,0,'내용 테스트.......','127.0.0.1','sample.txt');

commit;

create table noticeBoard(
   	num number,
   	writer varchar2(20),
 	email varchar2(30),
	subject varchar2(50),
	reg_date date,
	readcount number default 0, 
	ref number, 
	re_step number, 
	re_level number, 
	content varchar2(100),
	ip varchar2(20),
    upload varchar2(300)
);

create sequence noticeBoard_num_seq
start with 1 
increment by 1
nocache
nocycle;

insert into noticeBoard 
values(noticeBoard_num_seq.nextval, '홍길동','young@aaaa.com','제목1',sysdate,0,noticeBoard_num_seq.nextval,
0,0,'내용 테스트.......','127.0.0.1','sample.txt');

commit;

create table freeBoard(
   	num number,
   	writer varchar2(20),
 	email varchar2(30),
	subject varchar2(50),
	reg_date date,
	readcount number default 0, 
	ref number, 
	re_step number, 
	re_level number, 
	content varchar2(100),
	ip varchar2(20),
    upload varchar2(300)
);

create sequence freeBoard_num_seq
start with 1 
increment by 1
nocache
nocycle;

insert into freeBoard 
values(freeBoard_num_seq.nextval, '홍길동','young@aaaa.com','제목1',sysdate,0,freeBoard_num_seq.nextval,
0,0,'내용 테스트.......','127.0.0.1','sample.txt');

commit;

create table reviewBoard(
   	num number,
   	writer varchar2(20),
 	email varchar2(30),
	subject varchar2(50),
	reg_date date,
	readcount number default 0, 
	ref number, 
	re_step number, 
	re_level number, 
	content varchar2(100),
	ip varchar2(20),
    upload varchar2(300)
);

create sequence reviewBoard_num_seq
start with 1 
increment by 1
nocache
nocycle;

insert into reviewBoard 
values(reviewBoard_num_seq.nextval, '홍길동','young@aaaa.com','제목1',sysdate,0,reviewBoard_num_seq.nextval,
0,0,'내용 테스트.......','127.0.0.1','sample.txt');

commit;