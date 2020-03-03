INSERT INTO `user` (`created_at`, `is_enabled`, `updated_at`, `user_balance`, `user_email`, `user_name`, `user_password`) 
SELECT '2020-03-02 00:00:00.000000', b'0', '2020-03-02 00:00:00.000000', 0, 'v@v.com', 'vishva', '$2y$12$glotx15sU/dgkHyNpG1zYuUE9.yYFkIiWx.j4VSrz4Cl8N2fhEiUC'
WHERE NOT EXISTS (SELECT * FROM  `user` WHERE `user_id`=1);

