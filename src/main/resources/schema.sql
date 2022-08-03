

/* 食材マスタ */
CREATE TABLE IF NOT EXISTS food (
    Id VARCHAR(50) AUTO_INCREMENT PRIMARY KEY
  , email VARCHAR(50) 
  , name VARCHAR(500)
  , limitday DATE
);

/* ユーザーマスタ */
CREATE TABLE IF NOT EXISTS m_user (
    userId VARCHAR(50) AUTO_INCREMENT  PRIMARY KEY
  , userName VARCHAR(50) 
  , email VARCHAR(50)
  , password VARCHAR(50)
);

