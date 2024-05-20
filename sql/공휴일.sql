-- Inserting Situation for holidays
INSERT INTO situation (category) VALUES ('DATE');

-- Inserting DetailedSituation for JP holidays (without language)
INSERT INTO detailed_situation (name, situation_id) VALUES ('元日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('成人の日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('建国記念の日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('天皇誕生日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('春分の日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('昭和の日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('憲法記念日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('みどりの日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('こどもの日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('海の日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('山の日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('敬老の日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('秋分の日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('スポーツの日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('文化の日', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('勤労感謝の日', 1);

-- Inserting SituationSentence for JP holidays
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は元日です！', '오늘은 새해 첫날입니다!', 1);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は成人の日です！', '오늘은 성인의 날입니다!', 2);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は建国記念の日です！', '오늘은 건국 기념일입니다!', 3);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は天皇誕生日です！', '오늘은 천황 탄생일입니다!', 4);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は春分の日です！', '오늘은 춘분입니다!', 5);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は昭和の日です！', '오늘은 쇼와의 날입니다!', 6);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は憲法記念日です！', '오늘은 헌법 기념일입니다!', 7);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日はみどりの日です！', '오늘은 녹색의 날입니다!', 8);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日はこどもの日です！', '오늘은 어린이날입니다!', 9);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は海の日です！', '오늘은 바다의 날입니다!', 10);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は山の日です！', '오늘은 산의 날입니다!', 11);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は敬老の日です！', '오늘은 경로의 날입니다!', 12);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は秋分の日です！', '오늘은 추분입니다!', 13);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日はスポーツの日です！', '오늘은 스포츠의 날입니다!', 14);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は文化の日です！', '오늘은 문화의 날입니다!', 15);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('JAPANESE', '今日は勤労感謝の日です！', '오늘은 노동 감사의 날입니다!', 16);

-- Inserting SituationImage for JP holidays
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/new_year_jp.jpg', 1);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/coming_of_age_day.jpg', 2);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/foundation_day.jpg', 3);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/emperor_birthday.jpg', 4);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/vernal_equinox_day.jpg', 5);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/showa_day.jpg', 6);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/constitution_memorial_day.jpg', 7);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/greenery_day.jpg', 8);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/children_day.jpg', 9);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/marine_day.jpg', 10);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/mountain_day.jpg', 11);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/respect_aged_day.jpg', 12);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/autumnal_equinox_day.jpg', 13);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/sports_day.jpg', 14);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/culture_day.jpg', 15);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/labour_thanksgiving_day.jpg', 16);

-- Inserting DetailedSituation for US holidays (without language)
INSERT INTO detailed_situation (name, situation_id) VALUES ('New Year\'s Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Martin Luther King, Jr. Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Presidents Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Good Friday', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Memorial Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Juneteenth National Independence Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Independence Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Labor Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Columbus Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Indigenous Peoples\' Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Veterans Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Thanksgiving Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Christmas Day', 1);
INSERT INTO detailed_situation (name, situation_id) VALUES ('Washington\'s Birthday', 1);

-- Inserting SituationSentence for US holidays
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is New Year''s Day!', '오늘은 새해 첫날입니다!', 17);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Martin Luther King, Jr. Day!', '오늘은 마틴 루터 킹 주니어 데이입니다!', 18);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Presidents Day!', '오늘은 대통령의 날입니다!', 19);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Good Friday!', '오늘은 성금요일입니다!', 20);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Memorial Day!', '오늘은 현충일입니다!', 21);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Juneteenth National Independence Day!', '오늘은 준틴스 독립 기념일입니다!', 22);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Independence Day!', '오늘은 독립기념일입니다!', 23);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Labor Day!', '오늘은 노동절입니다!', 24);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Columbus Day!', '오늘은 콜럼버스의 날입니다!', 25);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Indigenous Peoples'' Day!', '오늘은 원주민의 날입니다!', 26);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Veterans Day!', '오늘은 재향군인의 날입니다!', 27);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Thanksgiving Day!', '오늘은 추수감사절입니다!', 28);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Christmas Day!', '오늘은 크리스마스입니다!', 29);
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES ('ENGLISH', 'Today is Washington''s Birthday!', '오늘은 워싱턴의 생일입니다!', 30);

-- Inserting SituationImage for US holidays
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/new_year_us.jpg', 17);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/mlk_day.jpg', 18);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/presidents_day.jpg', 19);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/good_friday.jpg', 20);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/memorial_day.jpg', 21);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/juneteenth.jpg', 22);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/independence_day.jpg', 23);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/labor_day.jpg', 24);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/columbus_day.jpg', 25);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/indigenous_peoples_day.jpg', 26);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/veterans_day.jpg', 27);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/thanksgiving_day.jpg', 28);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/christmas_day.jpg', 29);
INSERT INTO situation_image (url, detailed_situation_id) VALUES ('http://example.com/washington_birthday.jpg', 30);
