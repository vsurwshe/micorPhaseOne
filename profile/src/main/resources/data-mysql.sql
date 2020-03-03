/*INSERT INTO `profile` (`profile_id`, `created_at`, `profile_name`, `updated_at`, `version`, `user`, `features`, `type`) VALUES
(1, '2020-02-25 06:02:44.208000', 'Vishva', '2020-02-25 06:02:44.208000', 0, 1, 0, 0),
(3, '2020-02-25 06:29:19.746000', 'Akash', '2020-02-25 06:29:19.746000', 0, 1, 0, 0);*/


INSERT INTO `profile` (`created_at`, `profile_name`, `updated_at`, `version`, `user`, `features`, `type`)
SELECT '2020-02-25 06:02:44.208000', 'Vishva', '2020-02-25 06:02:44.208000', 0, 1, 0, 0
WHERE NOT EXISTS (SELECT * FROM `profile` WHERE profile_id=1);

INSERT INTO `profile` (`created_at`, `profile_name`, `updated_at`, `version`, `user`, `features`, `type`)
SELECT '2020-02-25 06:29:19.746000', 'Akash', '2020-02-25 06:29:19.746000', 0, 1, 0, 0
WHERE NOT EXISTS (SELECT * FROM `profile` WHERE profile_id=2);

INSERT INTO `profiles_set` (`cost`, `created_at`, `features`, `name`, `type`, `updated_at`, `version`)
SELECT  0, '2020-02-28 00:00:00.000000', 'Limited Labels, Limited Categories', 'free', 0, '2020-02-28 00:00:00.000000', 0
WHERE NOT EXISTS (SELECT * FROM  `profiles_set` WHERE id=1);

INSERT INTO `profiles_set` (`cost`, `created_at`, `features`, `name`, `type`, `updated_at`, `version`)
SELECT 100, '2020-02-28 00:00:00.000000', 'Unlimited Lables, Unlimited Catgoreis', 'Preninum', 1, '2020-02-28 00:00:00.000000', 0
WHERE NOT EXISTS (SELECT * FROM  `profiles_set` WHERE id=2);
