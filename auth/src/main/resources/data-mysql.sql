INSERT INTO `user` (`created_at`, `is_enabled`, `updated_at`, `user_balance`, `user_email`, `user_name`, `user_password`) 
SELECT '2020-03-05 09:31:35.149000', b'0', '2020-03-05 09:31:35.149000', 0, 'm@m.com', 'akash', '$2a$10$87ZN/.okr88Ov7H7yxZJ7eYO1I3rwHslE1UATpnTXtoBcnWdFGwp2'
WHERE NOT EXISTS (SELECT * FROM  `user` WHERE `user_id`=1);

INSERT INTO `user` (`created_at`, `is_enabled`, `updated_at`, `user_balance`, `user_email`, `user_name`, `user_password`) 
SELECT '2020-03-05 09:34:11.924000', b'0', '2020-03-05 09:34:11.924000', 0, 'v@v.com', 'vishvanath', '$2a$10$wGqni35EnbTZ9LtFM.InrO8EC83NyjN3Jc/17fOCFgZv.9zY6u09a'
WHERE NOT EXISTS (SELECT * FROM  `user` WHERE `user_id`=2);

