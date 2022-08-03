
/* ユーザーマスタ */
INSERT INTO food (
    Id
  , email
  , name
  , limitday
) VALUES
(1,'imagawa.yuuki.0827@gmail.com',  'りんご', '2022-08-03')
, (2,'imagawa.yuuki.0827@gmail.com',  'たまご', '2022-08-05')
, (3,'imagawa.yuuki.0827@gmail.com',  'たまねぎ', '2022-08-20')
, (4,'imagawa.yuuki.0827@gmail.com',  'りんご', '2022-08-21')
, (5,'imagawa.yuuki.0827@gmail.com',  '牛肉', '2022-08-03')
, (6,'imagawa.yuuki.0827@gmail.com',  'トマト', '2022-05-29')
, (7,'imagawa.yuuki.0827@gmail.com',  'さんま', '2022-05-29')
, (8,'imagawa.yuuki.0827@gmail.com',  '豚肉', '2022-06-01')
, (9,'imagawa.yuuki.0827@gmail.com',  '牛乳', '2022-05-27')
, (10,'imagawa.yuuki.0827@gmail.com',  'にんにく', '2022-07-01')
, (11,'system@co.jp','たまご','2022-08-05')
, (12,'system@co.jp',  'たまねぎ', '2022-08-20')
, (13,'system@co.jp',  '豚肉', '2022-08-04');

 
INSERT INTO m_user(
     userId
   , userName
   , email
   , password
) VALUES
(1,'aiu','imagawa.yuuki.0827@gmail.com','ddwd')
, (2,'e','system@co.jp','password');



