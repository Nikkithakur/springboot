insert into ACCOUNT_ENTITY (BALANCE, ALIAS_NAME, NAME, PHONE_NUMBER) 
values (17890.98, 'Nick','NikhilPrasad','9032858312'),
(1789890.98, 'Batsy','Batman','9032858312');

insert into PAYMENT_ENTITY (MESSAGE) 
values ('$12000 credited'),
('$2000 credited'),
('$20000 credited');

insert into ACCOUNT_ENTITY_PAYMENTS
values (1,1),
(2,2),
(2,3);