INSERT INTO `user` (`created_at`, `is_enabled`, `name`, `updated_at`, `user_email`, `user_password`) 
SELECT '2020-02-28 00:00:00.000000', 1, 'vishva', '2020-02-28 00:00:00.000000', 'v@v.com', '$2y$12$glotx15sU/dgkHyNpG1zYuUE9.yYFkIiWx.j4VSrz4Cl8N2fhEiUC'
WHERE NOT EXISTS (SELECT * FROM  `user` WHERE `user_id`=1);

