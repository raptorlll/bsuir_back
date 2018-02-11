create table consultant_group
(
	id bigint auto_increment
		primary key,
	name varchar(50) not null,
	description varchar(255) null,
	video_tarif int not null,
	conversation_tarif int not null
)
engine=InnoDB
;

create table consultant_group_user
(
	user_id bigint not null,
	consultant_grout_id bigint not null,
	id bigint auto_increment
		primary key,
	status tinyint(1) default '0' null,
	video_tarif int null,
	conversation_tarif int null,
	constraint consultant__group_user_consultant_group_id_fk
		foreign key (consultant_grout_id) references consultant_group (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index consultant__group_user_user_id_fk
	on consultant_group_user (user_id)
;

create index consultant__group_user_consultant_group_id_fk
	on consultant_group_user (consultant_grout_id)
;

create table consultant_information
(
	id bigint auto_increment
		primary key,
	education varchar(50) not null,
	degree varchar(50) not null,
	license_number varchar(50) not null,
	license_file varchar(50) null,
	license_until date not null,
	available_from time not null,
	available_until time not null,
	consultant_group_user_id bigint not null,
	constraint consultant_information_consultant__group_user_user_id_fk
		foreign key (consultant_group_user_id) references consultant_group_user (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index consultant_information_consultant__group_user_user_id_fk
	on consultant_information (consultant_group_user_id)
;

create table conversation
(
	id bigint auto_increment
		primary key,
	consultant_group_user_id bigint not null,
	customer_information_id bigint not null,
	active tinyint(1) default '1' not null,
	constraint conversation_consultant__group_user_id_fk
		foreign key (consultant_group_user_id) references consultant_group_user (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index conversation_consultant__group_user_id_fk
	on conversation (consultant_group_user_id)
;

create index conversation_customer_information_id_fk
	on conversation (customer_information_id)
;

create table conversation_message
(
	id bigint auto_increment
		primary key,
	conversation_id bigint not null,
	message text not null,
	is_consultant_message tinyint(1) default '0' null,
	date_time datetime not null,
	attached_file varchar(50) null,
	video_duration time null,
	video_external_link varchar(50) null,
	constraint conversation_message_conversation_id_fk
		foreign key (conversation_id) references conversation (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index conversation_message_conversation_id_fk
	on conversation_message (conversation_id)
;

create index conversation_message_conversation_video_id_fk
	on conversation_message (video_duration)
;

create table conversation_status
(
	id bigint auto_increment
		primary key,
	name varchar(20) not null,
	description varchar(50) null
)
engine=InnoDB
;

create table conversation_status_history
(
	id bigint auto_increment
		primary key,
	conversation_id bigint not null,
	conversation_status_id bigint not null,
	date_time datetime not null,
	constraint conversation_status_history_conversation_id_fk
		foreign key (conversation_id) references conversation (id)
			on update cascade on delete cascade,
	constraint conversation_status_history_conversation_status_id_fk
		foreign key (conversation_status_id) references conversation_status (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index conversation_status_history_conversation_id_fk
	on conversation_status_history (conversation_id)
;

create index conversation_status_history_conversation_status_id_fk
	on conversation_status_history (conversation_status_id)
;

create table customer_information
(
	id bigint auto_increment
		primary key,
	user_id bigint null,
	birth_data date null,
	additional_information text null,
	is_primary tinyint(1) default '1' not null
)
engine=InnoDB
;

create index user_information_user_id_fk
	on customer_information (user_id)
;

alter table conversation
	add constraint conversation_customer_information_id_fk
		foreign key (customer_information_id) references customer_information (id)
			on update cascade on delete cascade
;

create table customer_payment
(
	id bigint auto_increment
		primary key,
	data_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	conversation_id bigint not null,
	amount bigint null,
	constraint customer_payment_conversation_id_fk
		foreign key (conversation_id) references conversation (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index customer_payment_conversation_id_fk
	on customer_payment (conversation_id)
;

create table role
(
	id bigint auto_increment
		primary key,
	description varchar(255) null,
	role_name varchar(255) null
)
engine=InnoDB
;

create table user
(
	id bigint auto_increment
		primary key,
	password varchar(255) not null,
	username varchar(255) not null,
	email varchar(255) not null,
	first_name varchar(50) not null,
	last_name varchar(50) not null
)
engine=InnoDB
;

alter table consultant_group_user
	add constraint consultant__group_user_user_id_fk
		foreign key (user_id) references user (id)
			on update cascade on delete cascade
;

alter table customer_information
	add constraint user_information_user_id_fk
		foreign key (user_id) references user (id)
;

create table user_role
(
	user_id bigint not null,
	role_id bigint not null,
	constraint FK859n2jvi8ivhui0rl0esws6o
		foreign key (user_id) references user (id),
	constraint FKa68196081fvovjhkek5m97n3y
		foreign key (role_id) references role (id)
)
engine=InnoDB
;

create index FK859n2jvi8ivhui0rl0esws6o
	on user_role (user_id)
;

create index FKa68196081fvovjhkek5m97n3y
	on user_role (role_id)
;

