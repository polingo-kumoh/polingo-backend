-- Inserting Situation for holidays
-- INSERT INTO situation (id, category) VALUES (1, 'DATE');

-- Inserting DetailedSituation for JP holidays
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (1, '元日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (2, '成人の日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (3, '建国記念の日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (4, '天皇誕生日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (5, '春分の日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (6, '昭和の日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (7, '憲法記念日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (8, 'みどりの日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (9, 'こどもの日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (10, '海の日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (11, '山の日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (12, '敬老の日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (13, '秋分の日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (14, 'スポーツの日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (15, '文化の日', 1, 'JAPANESE');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (16, '勤労感謝の日', 1, 'JAPANESE');

-- Inserting SituationSentence for JP holidays
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (1, '今日は元日です！', 'Today is New Year\'s Day!', 1);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (2, '今日は成人の日です！', 'Today is Coming of Age Day!', 2);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (3, '今日は建国記念の日です！', 'Today is Foundation Day!', 3);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (4, '今日は天皇誕生日です！', 'Today is The Emperor\'s Birthday!', 4);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (5, '今日は春分の日です！', 'Today is Vernal Equinox Day!', 5);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (6, '今日は昭和の日です！', 'Today is Shōwa Day!', 6);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (7, '今日は憲法記念日です！', 'Today is Constitution Memorial Day!', 7);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (8, '今日はみどりの日です！', 'Today is Greenery Day!', 8);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (9, '今日はこどもの日です！', 'Today is Children\'s Day!', 9);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (10, '今日は海の日です！', 'Today is Marine Day!', 10);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (11, '今日は山の日です！', 'Today is Mountain Day!', 11);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (12, '今日は敬老の日です！', 'Today is Respect for the Aged Day!', 12);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (13, '今日は秋分の日です！', 'Today is Autumnal Equinox Day!', 13);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (14, '今日はスポーツの日です！', 'Today is Sports Day!', 14);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (15, '今日は文化の日です！', 'Today is Culture Day!', 15);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (16, '今日は勤労感謝の日です！', 'Today is Labour Thanksgiving Day!', 16);

-- Inserting SituationImage for JP holidays
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (1, 'http://example.com/new_year_jp.jpg', 1);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (2, 'http://example.com/coming_of_age_day.jpg', 2);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (3, 'http://example.com/foundation_day.jpg', 3);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (4, 'http://example.com/emperor_birthday.jpg', 4);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (5, 'http://example.com/vernal_equinox_day.jpg', 5);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (6, 'http://example.com/showa_day.jpg', 6);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (7, 'http://example.com/constitution_memorial_day.jpg', 7);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (8, 'http://example.com/greenery_day.jpg', 8);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (9, 'http://example.com/children_day.jpg', 9);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (10, 'http://example.com/marine_day.jpg', 10);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (11, 'http://example.com/mountain_day.jpg', 11);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (12, 'http://example.com/respect_aged_day.jpg', 12);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (13, 'http://example.com/autumnal_equinox_day.jpg', 13);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (14, 'http://example.com/sports_day.jpg', 14);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (15, 'http://example.com/culture_day.jpg', 15);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (16, 'http://example.com/labour_thanksgiving_day.jpg', 16);

-- Inserting DetailedSituation for US holidays
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (17, 'New Year\'s Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (18, 'Martin Luther King, Jr. Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (19, 'Presidents Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (20, 'Good Friday', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (21, 'Memorial Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (22, 'Juneteenth National Independence Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (23, 'Independence Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (24, 'Labor Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (25, 'Columbus Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (26, 'Indigenous Peoples\' Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (27, 'Veterans Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (28, 'Thanksgiving Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (29, 'Christmas Day', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (30, 'Washington\'s Birthday', 1, 'ENGLISH');
INSERT INTO detailed_situation (id, name, situation_id, language) VALUES (31, 'Labour Day', 1, 'ENGLISH');

-- Inserting SituationSentence for US holidays
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (17, 'Today is New Year\'s Day!', '오늘은 새해 첫날입니다!', 17);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (18, 'Today is Martin Luther King, Jr. Day!', '오늘은 마틴 루터 킹 주니어 데이입니다!', 18);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (19, 'Today is Presidents Day!', '오늘은 대통령의 날입니다!', 19);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (20, 'Today is Good Friday!', '오늘은 성금요일입니다!', 20);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (21, 'Today is Memorial Day!', '오늘은 현충일입니다!', 21);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (22, 'Today is Juneteenth National Independence Day!', '오늘은 준틴스 독립 기념일입니다!', 22);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (23, 'Today is Independence Day!', '오늘은 독립기념일입니다!', 23);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (24, 'Today is Labor Day!', '오늘은 노동절입니다!', 24);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (25, 'Today is Columbus Day!', '오늘은 콜럼버스의 날입니다!', 25);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (26, 'Today is Indigenous Peoples\' Day!', '오늘은 원주민의 날입니다!', 26);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (27, 'Today is Veterans Day!', '오늘은 재향군인의 날입니다!', 27);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (28, 'Today is Thanksgiving Day!', '오늘은 추수감사절입니다!', 28);
INSERT INTO situation_sentence (id, sentence, translation, detailed_situation_id) VALUES (29, 'Today is Christmas Day!', '오늘은 크리스마스입니다!', 29);

-- Inserting SituationImage for US holidays
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (17, 'http://example.com/new_year_us.jpg', 17);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (18, 'http://example.com/mlk_day.jpg', 18);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (19, 'http://example.com/presidents_day.jpg', 19);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (20, 'http://example.com/good_friday.jpg', 20);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (21, 'http://example.com/memorial_day.jpg', 21);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (22, 'http://example.com/juneteenth.jpg', 22);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (23, 'http://example.com/independence_day.jpg', 23);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (24, 'http://example.com/labor_day.jpg', 24);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (25, 'http://example.com/columbus_day.jpg', 25);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (26, 'http://example.com/indigenous_peoples_day.jpg', 26);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (27, 'http://example.com/veterans_day.jpg', 27);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (28, 'http://example.com/thanksgiving_day.jpg', 28);
INSERT INTO situation_image (id, url, detailed_situation_id) VALUES (29, 'http://example.com/christmas_day.jpg', 29);
