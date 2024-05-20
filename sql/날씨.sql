INSERT INTO situation (category) VALUES ('WEATHER');

-- Inserting DetailedSituation for Thunderstorm
INSERT INTO detailed_situation (name, situation_id) VALUES ('thunderstorm with light rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('thunderstorm with rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('thunderstorm with heavy rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('light thunderstorm', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('thunderstorm', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('heavy thunderstorm', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('ragged thunderstorm', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('thunderstorm with light drizzle', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('thunderstorm with drizzle', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('thunderstorm with heavy drizzle', 2);

-- Inserting DetailedSituation for Drizzle
INSERT INTO detailed_situation (name, situation_id) VALUES ('light intensity drizzle', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('drizzle', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('heavy intensity drizzle', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('light intensity drizzle rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('drizzle rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('heavy intensity drizzle rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('shower rain and drizzle', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('heavy shower rain and drizzle', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('shower drizzle', 2);

-- Inserting DetailedSituation for Rain
INSERT INTO detailed_situation (name, situation_id) VALUES ('light rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('moderate rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('heavy intensity rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('very heavy rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('extreme rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('freezing rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('light intensity shower rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('shower rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('heavy intensity shower rain', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('ragged shower rain', 2);

-- Inserting DetailedSituation for Snow
INSERT INTO detailed_situation (name, situation_id) VALUES ('light snow', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('snow', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('heavy snow', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('sleet', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('light shower sleet', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('shower sleet', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('light rain and snow', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('rain and snow', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('light shower snow', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('shower snow', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('heavy shower snow', 2);

-- Inserting DetailedSituation for Atmosphere
INSERT INTO detailed_situation (name, situation_id) VALUES ('mist', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('smoke', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('haze', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('sand/ dust whirls', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('fog', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('sand', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('dust', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('volcanic ash', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('squalls', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('tornado', 2);

-- Inserting DetailedSituation for Clear
INSERT INTO detailed_situation (name, situation_id) VALUES ('clear sky', 2);

-- Inserting DetailedSituation for Clouds
INSERT INTO detailed_situation (name, situation_id) VALUES ('few clouds (11-25%)', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('scattered clouds (25-50%)', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('broken clouds (51-84%)', 2);
INSERT INTO detailed_situation (name, situation_id) VALUES ('overcast clouds (85-100%)', 2);

INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES
                                                                                            ('ENGLISH', 'Thunderstorm with light rain. Take an umbrella when going out.', '약한 비가 동반된 천둥번개가 칩니다. 외출 시 우산을 챙기세요.', 38),
                                                                                            ('ENGLISH', 'Thunderstorm with rain. Stay safe.', '비가 동반된 천둥번개가 칩니다. 안전에 유의하세요.', 39),
                                                                                            ('ENGLISH', 'Thunderstorm with heavy rain. Avoid going out if possible.', '강한 비가 동반된 천둥번개가 칩니다. 외출을 삼가세요.', 40),
                                                                                            ('ENGLISH', 'Light thunderstorm. Be cautious during outdoor activities.', '약한 천둥번개가 칩니다. 외부활동에 조심하세요.', 41),
                                                                                            ('ENGLISH', 'Thunderstorm. Stay safe.', '천둥번개가 칩니다. 안전에 주의하세요.', 42),
                                                                                            ('ENGLISH', 'Heavy thunderstorm. Refrain from going out.', '강한 천둥번개가 칩니다. 외출을 자제하세요.', 43),
                                                                                            ('ENGLISH', 'Ragged thunderstorm. Be cautious just in case.', '불규칙한 천둥번개가 칩니다. 혹시 모르니 주의하세요.', 44),
                                                                                            ('ENGLISH', 'Thunderstorm with light drizzle. Take an umbrella.', '약한 이슬비가 동반된 천둥번개가 칩니다. 우산을 챙기세요.', 45),
                                                                                            ('ENGLISH', 'Thunderstorm with drizzle. Be careful.', '이슬비가 동반된 천둥번개가 칩니다. 주의하세요.', 46),
                                                                                            ('ENGLISH', 'Thunderstorm with heavy drizzle. Avoid going out.', '강한 이슬비가 동반된 천둥번개가 칩니다. 외출을 피하세요.', 47),
                                                                                            ('ENGLISH', 'Light intensity drizzle. Take an umbrella.', '약한 이슬비가 내립니다. 우산을 챙기세요.', 48),
                                                                                            ('ENGLISH', 'Drizzle. Take a light umbrella just in case.', '이슬비가 내립니다. 혹시 모르니 가벼운 우산을 준비하세요.', 49),
                                                                                            ('ENGLISH', 'Heavy intensity drizzle. Don’t forget your umbrella when going out.', '강한 이슬비가 내립니다. 외출 시 우산을 잊지 마세요.', 50),
                                                                                            ('ENGLISH', 'Light intensity drizzle rain. Prepare an umbrella.', '약한 이슬비가 섞인 비가 내립니다. 우산을 준비하세요.', 51),
                                                                                            ('ENGLISH', 'Drizzle rain. Be careful.', '이슬비가 섞인 비가 내립니다. 주의하세요.', 52),
                                                                                            ('ENGLISH', 'Heavy intensity drizzle rain. Refrain from going out.', '강한 이슬비가 섞인 비가 내립니다. 외출을 자제하세요.', 53),
                                                                                            ('ENGLISH', 'Shower rain and drizzle. Take an umbrella.', '소나기와 이슬비가 섞여 내립니다. 우산을 챙기세요.', 54),
                                                                                            ('ENGLISH', 'Heavy shower rain and drizzle. Be careful when going out.', '강한 소나기와 이슬비가 섞여 내립니다. 외출 시 주의하세요.', 55),
                                                                                            ('ENGLISH', 'Shower drizzle. Prepare a light umbrella.', '소나기와 이슬비가 섞여 내립니다. 가벼운 우산을 준비하세요.', 56),
                                                                                            ('ENGLISH', 'Light rain. Prepare an umbrella.', '약한 비가 내립니다. 우산을 준비하세요.', 57),
                                                                                            ('ENGLISH', 'Moderate rain. Take an umbrella.', '비가 내립니다. 우산을 챙기세요.', 58),
                                                                                            ('ENGLISH', 'Heavy intensity rain. Be cautious when going out.', '강한 비가 내립니다. 외출 시 주의하세요.', 59),
                                                                                            ('ENGLISH', 'Very heavy rain. Avoid going out if possible.', '매우 강한 비가 내립니다. 외출을 자제하세요.', 60),
                                                                                            ('ENGLISH', 'Extreme rain. Stay indoors if possible.', '극심한 비가 내립니다. 외출을 피하세요.', 61),
                                                                                            ('ENGLISH', 'Freezing rain. Be careful of slippery surfaces.', '비가 얼어붙고 있습니다. 미끄럼에 주의하세요.', 62),
                                                                                            ('ENGLISH', 'Light intensity shower rain. Take an umbrella.', '약한 소나기가 내립니다. 우산을 챙기세요.', 63),
                                                                                            ('ENGLISH', 'Shower rain. Prepare an umbrella.', '소나기가 내립니다. 우산을 준비하세요.', 64),
                                                                                            ('ENGLISH', 'Heavy intensity shower rain. Be careful when going out.', '강한 소나기가 내립니다. 외출 시 주의하세요.', 65),
                                                                                            ('ENGLISH', 'Ragged shower rain. Don’t forget your umbrella.', '불규칙한 소나기가 내립니다. 우산을 잊지 마세요.', 66),
                                                                                            ('ENGLISH', 'Light snow. Dress warmly.', '약한 눈이 내립니다. 따뜻하게 입으세요.', 67),
                                                                                            ('ENGLISH', 'Snow. Be careful when going out.', '눈이 내립니다. 외출 시 주의하세요.', 68),
                                                                                            ('ENGLISH', 'Heavy snow. Avoid going out if possible.', '많은 눈이 내립니다. 외출을 자제하세요.', 69),
                                                                                            ('ENGLISH', 'Sleet. Be careful of slippery surfaces.', '진눈깨비가 내립니다. 미끄럼에 주의하세요.', 70),
                                                                                            ('ENGLISH', 'Light shower sleet. Prepare an umbrella.', '약한 소나기 진눈깨비가 내립니다. 우산을 준비하세요.', 71),
                                                                                            ('ENGLISH', 'Shower sleet. Be careful when going out.', '소나기 진눈깨비가 내립니다. 외출 시 주의하세요.', 72),
                                                                                            ('ENGLISH', 'Light rain and snow. Prepare an umbrella and warm clothes.', '약한 비와 눈이 섞여 내립니다. 우산과 따뜻한 옷을 준비하세요.', 73),
                                                                                            ('ENGLISH', 'Rain and snow. Be careful when going out.', '비와 눈이 섞여 내립니다. 외출 시 주의하세요.', 74),
                                                                                            ('ENGLISH', 'Light shower snow. Dress warmly.', '약한 소나기 눈이 내립니다. 따뜻하게 입으세요.', 75),
                                                                                            ('ENGLISH', 'Shower snow. Be careful when going out.', '소나기 눈이 내립니다. 외출 시 주의하세요.', 76),
                                                                                            ('ENGLISH', 'Heavy shower snow. Avoid going out if possible.', '강한 소나기 눈이 내립니다. 외출을 자제하세요.', 77),
                                                                                            ('ENGLISH', 'Mist. Be cautious when driving.', '안개가 끼어 있습니다. 운전 시 주의하세요.', 78),
                                                                                            ('ENGLISH', 'Smoke. Stay indoors.', '연기가 끼어 있습니다. 실내에서 활동하세요.', 79),
                                                                                            ('ENGLISH', 'Haze. Be cautious of visibility.', '옅은 안개가 끼어 있습니다. 시야에 주의하세요.', 80),
                                                                                            ('ENGLISH', 'Sand/dust whirls. Wear protective gear.', '모래/먼지가 소용돌이치고 있습니다. 보호 장비를 착용하세요.', 81),
                                                                                            ('ENGLISH', 'Fog. Be extremely cautious when driving.', '짙은 안개가 끼어 있습니다. 운전 시 매우 주의하세요.', 82),
                                                                                            ('ENGLISH', 'Sand is blowing. Stay indoors.', '모래가 날리고 있습니다. 실내에 머무르세요.', 83),
                                                                                            ('ENGLISH', 'Dust is blowing. Wear a mask.', '먼지가 날리고 있습니다. 마스크를 꼭 착용하세요.', 84),
                                                                                            ('ENGLISH', 'Volcanic ash is in the air. Stay indoors.', '화산재가 날리고 있습니다. 실내에 머무르세요.', 85),
                                                                                            ('ENGLISH', 'Squalls are present. Avoid going out.', '돌풍이 있습니다. 외출을 자제하세요.', 86),
                                                                                            ('ENGLISH', 'Tornado has occurred. Seek shelter in a safe place.', '토네이도가 발생했습니다. 안전한 장소로 대피하세요.', 87),
                                                                                            ('ENGLISH', 'Clear sky. Have a pleasant day.', '하늘이 맑습니다. 기분 좋은 하루 되세요.', 88),
                                                                                            ('ENGLISH', 'There are a few clouds in the sky, but it’s mostly clear. It''s a great day for a walk or outdoor activities.', '오늘 하늘에 약간의 구름이 있지만, 대체로 맑은 날씨입니다. 산책이나 야외 활동을 즐기기 좋은 날입니다.', 89),
                                                                                            ('ENGLISH', 'Scattered clouds are in the sky. The sunlight peeking through the clouds makes for pleasant weather.', '오늘 하늘에는 구름이 드문드문 떠 있습니다. 햇빛이 구름 사이로 비춰 기분 좋은 날씨입니다.', 90),
                                                                                            ('ENGLISH', 'There are many clouds in the sky. It might not be the best day for a picnic or outdoor activities.', '구름이 많이 끼어 있습니다. 피크닉이나 소풍 활동 하기에는 살짝 부적합할 수도 있습니다.', 91),
                                                                                            ('ENGLISH', 'The sky is overcast and covered with clouds. There might be a chance of rain, so take an umbrella.', '오늘은 하늘이 흐리고 구름으로 덮여 있습니다. 비가 올 가능성이 있으니 우산을 챙기세요.', 92);

-- Inserting Japanese sentences into situation_sentence table with Korean translations
INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES
                                                                                            ('JAPANESE', '弱い雨を伴う雷雨があります。外出時には傘を持って行ってください。', '약한 비가 동반된 천둥번개가 칩니다. 외출 시 우산을 챙기세요.', 38),
                                                                                            ('JAPANESE', '雨を伴う雷雨があります。安全に注意してください。', '비가 동반된 천둥번개가 칩니다. 안전에 유의하세요.', 39),
                                                                                            ('JAPANESE', '強い雨を伴う雷雨があります。外出を控えてください。', '강한 비가 동반된 천둥번개가 칩니다. 외출을 삼가세요.', 40),
                                                                                            ('JAPANESE', '弱い雷雨があります。外での活動に注意してください。', '약한 천둥번개가 칩니다. 외부활동에 조심하세요.', 41),
                                                                                            ('JAPANESE', '雷雨があります。安全に注意してください。', '천둥번개가 칩니다. 안전에 주의하세요.', 42),
                                                                                            ('JAPANESE', '強い雷雨があります。外出を控えてください。', '강한 천둥번개가 칩니다. 외출을 자제하세요.', 43),
                                                                                            ('JAPANESE', '不規則な雷雨があります。念のため注意してください。', '불규칙한 천둥번개가 칩니다. 혹시 모르니 주의하세요.', 44),
                                                                                            ('JAPANESE', '弱い霧雨を伴う雷雨があります。傘を持って行ってください。', '약한 이슬비가 동반된 천둥번개가 칩니다. 우산을 챙기세요.', 45),
                                                                                            ('JAPANESE', '霧雨を伴う雷雨があります。注意してください。', '이슬비가 동반된 천둥번개가 칩니다. 주의하세요.', 46),
                                                                                            ('JAPANESE', '強い霧雨を伴う雷雨があります。外出を避けてください。', '강한 이슬비가 동반된 천둥번개가 칩니다. 외출을 피하세요.', 47),
                                                                                            ('JAPANESE', '弱い霧雨が降っています。傘を持って行ってください。', '약한 이슬비가 내립니다. 우산을 챙기세요.', 48),
                                                                                            ('JAPANESE', '霧雨が降っています。念のため軽い傘を準備してください。', '이슬비가 내립니다. 혹시 모르니 가벼운 우산을 준비하세요.', 49),
                                                                                            ('JAPANESE', '強い霧雨が降っています。外出時には傘を忘れないでください。', '강한 이슬비가 내립니다. 외출 시 우산을 잊지 마세요.', 50),
                                                                                            ('JAPANESE', '弱い霧雨が混じった雨が降っています。傘を準備してください。', '약한 이슬비가 섞인 비가 내립니다. 우산을 준비하세요.', 51),
                                                                                            ('JAPANESE', '霧雨が混じった雨が降っています。注意してください。', '이슬비가 섞인 비가 내립니다. 주의하세요.', 52),
                                                                                            ('JAPANESE', '強い霧雨が混じった雨が降っています。外出を控えてください。', '강한 이슬비가 섞인 비가 내립니다. 외출을 자제하세요.', 53),
                                                                                            ('JAPANESE', 'にわか雨と霧雨が混じっています。傘を持って行ってください。', '소나기와 이슬비가 섞여 내립니다. 우산을 챙기세요.', 54),
                                                                                            ('JAPANESE', '強いにわか雨と霧雨が混じっています。外出時に注意してください。', '강한 소나기와 이슬비가 섞여 내립니다. 외출 시 주의하세요.', 55),
                                                                                            ('JAPANESE', 'にわか雨と霧雨が混じっています。軽い傘を準備してください。', '소나기와 이슬비가 섞여 내립니다. 가벼운 우산을 준비하세요.', 56),
                                                                                            ('JAPANESE', '弱い雨が降っています。傘を準備してください。', '약한 비가 내립니다. 우산을 준비하세요.', 57),
                                                                                            ('JAPANESE', '雨が降っています。傘を持って行ってください。', '비가 내립니다. 우산을 챙기세요.', 58),
                                                                                            ('JAPANESE', '強い雨が降っています。外出時に注意してください。', '강한 비가 내립니다. 외출 시 주의하세요.', 59),
                                                                                            ('JAPANESE', '非常に強い雨が降っています。外出を控えてください。', '매우 강한 비가 내립니다. 외출을 자제하세요.', 60),
                                                                                            ('JAPANESE', '極端な雨が降っています。外出を避けてください。', '극심한 비가 내립니다. 외출을 피하세요.', 61),
                                                                                            ('JAPANESE', '雨が凍っています。滑りに注意してください。', '비가 얼어붙고 있습니다. 미끄럼에 주의하세요.', 62),
                                                                                            ('JAPANESE', '弱いにわか雨が降っています。傘を持って行ってください。', '약한 소나기가 내립니다. 우산을 챙기세요.', 63),
                                                                                            ('JAPANESE', 'にわか雨が降っています。傘を準備してください。', '소나기가 내립니다. 우산을 준비하세요.', 64),
                                                                                            ('JAPANESE', '強いにわか雨が降っています。外出時に注意してください。', '강한 소나기가 내립니다. 외출 시 주의하세요.', 65),
                                                                                            ('JAPANESE', '不規則なにわか雨が降っています。傘を忘れないでください。', '불규칙한 소나기가 내립니다. 우산을 잊지 마세요.', 66),
                                                                                            ('JAPANESE', '弱い雪が降っています。暖かくして出かけてください。', '약한 눈이 내립니다. 따뜻하게 입으세요.', 67),
                                                                                            ('JAPANESE', '雪が降っています。外出時に注意してください。', '눈이 내립니다. 외출 시 주의하세요.', 68),
                                                                                            ('JAPANESE', '大雪が降っています。外出を控えてください。', '많은 눈이 내립니다. 외출을 자제하세요.', 69),
                                                                                            ('JAPANESE', 'みぞれが降っています。滑りに注意してください。', '진눈깨비가 내립니다. 미끄럼에 주의하세요.', 70),
                                                                                            ('JAPANESE', '弱いにわかみぞれが降っています。傘を準備してください。', '약한 소나기 진눈깨비가 내립니다. 우산을 준비하세요.', 71),
                                                                                            ('JAPANESE', 'にわかみぞれが降っています。外出時に注意してください。', '소나기 진눈깨비가 내립니다. 외출 시 주의하세요.', 72),
                                                                                            ('JAPANESE', '弱い雨と雪が混じって降っています。傘と暖かい服を準備してください。', '약한 비와 눈이 섞여 내립니다. 우산과 따뜻한 옷을 준비하세요.', 73),
                                                                                            ('JAPANESE', '雨と雪が混じって降っています。外出時に注意してください。', '비와 눈이 섞여 내립니다. 외출 시 주의하세요.', 74),
                                                                                            ('JAPANESE', '弱いにわか雪が降っています。暖かくして出かけてください。', '약한 소나기 눈이 내립니다. 따뜻하게 입으세요.', 75),
                                                                                            ('JAPANESE', 'にわか雪が降っています。外出時に注意してください。', '소나기 눈이 내립니다. 외출 시 주의하세요.', 76),
                                                                                            ('JAPANESE', '強いにわか雪が降っています。外出を控えてください。', '강한 소나기 눈이 내립니다. 외출을 자제하세요.', 77),
                                                                                            ('JAPANESE', '霧がかかっています。運転時に注意してください。', '안개가 끼어 있습니다. 운전 시 주의하세요.', 78),
                                                                                            ('JAPANESE', '煙がかかっています。屋内で活動してください。', '연기가 끼어 있습니다. 실내에서 활동하세요.', 79),
                                                                                            ('JAPANESE', '薄霧がかかっています。視界に注意してください。', '옅은 안개가 끼어 있습니다. 시야에 주의하세요.', 80),
                                                                                            ('JAPANESE', '砂/ほこりの渦が巻いています。保護具を着用してください。', '모래/먼지가 소용돌이치고 있습니다. 보호 장비를 착용하세요.', 81),
                                                                                            ('JAPANESE', '濃い霧がかかっています。運転時に非常に注意してください。', '짙은 안개가 끼어 있습니다. 운전 시 매우 주의하세요.', 82),
                                                                                            ('JAPANESE', '砂が舞っています。屋内にとどまってください。', '모래가 날리고 있습니다. 실내에 머무르세요.', 83),
                                                                                            ('JAPANESE', 'ほこりが舞っています。マスクを必ず着用してください。', '먼지가 날리고 있습니다. 마스크를 꼭 착용하세요.', 84),
                                                                                            ('JAPANESE', '火山灰が舞っています。屋内で安全に過ごしてください。', '화산재가 날리고 있습니다. 실내에 머무르세요.', 85),
                                                                                            ('JAPANESE', 'スコールがあります。外出を控えてください。', '돌풍이 있습니다. 외출을 자제하세요.', 86),
                                                                                            ('JAPANESE', '竜巻が発生しました。安全な場所に避難してください。', '토네이도가 발생했습니다. 안전한 장소로 대피하세요.', 87),
                                                                                            ('JAPANESE', '空が晴れています。気持ちの良い一日をお過ごしください。', '하늘이 맑습니다. 기분 좋은 하루 되세요.', 88),
                                                                                            ('JAPANESE', '今日の空には少し雲がありますが、概ね晴れています。散歩や屋外活動に最適な日です。', '오늘 하늘에 약간의 구름이 있지만, 대체로 맑은 날씨입니다. 산책이나 야외 활동을 즐기기 좋은 날입니다.', 89),
                                                                                            ('JAPANESE', '今日の空には点々と雲があります。雲の間から太陽が覗いていて気持ちの良い天気です。', '오늘 하늘에는 구름이 드문드문 떠 있습니다. 햇빛이 구름 사이로 비춰 기분 좋은 날씨입니다.', 90),
                                                                                            ('JAPANESE', '空には多くの雲がかかっています。ピクニックや外での活動には少し不向きかもしれません。', '구름이 많이 끼어 있습니다. 피크닉이나 소풍 활동 하기에는 살짝 부적합할 수도 있습니다.', 91),
                                                                                            ('JAPANESE', '今日は空が曇っていて雲で覆われています。雨が降る可能性があるので傘を持って行ってください。', '오늘은 하늘이 흐리고 구름으로 덮여 있습니다. 비가 올 가능성이 있으니 우산을 챙기세요.', 92);


-- 02:25:42	INSERT INTO situation_sentence (language, sentence, translation, detailed_situation_id) VALUES                                                                                             ('ENGLISH', 'Thunderstorm with light rain. Take an umbrella when going out.', '약한 비가 동반된 천둥번개가 칩니다. 외출 시 우산을 챙기세요.', 38),                                                                                             ('ENGLISH', 'Thunderstorm with rain. Stay safe.', '비가 동반된 천둥번개가 칩니다. 안전에 유의하세요.', 39),                                                                                             ('ENGLISH', 'Thunderstorm with heavy rain. Avoid going out if possible.', '강한 비가 동반된 천둥번개가 칩니다. 외출을 삼가세요.', 40),                                                                                             ('ENGLISH', 'Light thunderstorm. Be cautious during outdoor activities.', '약한 천둥번개가 칩니다. 외부활동에 조심하세요.', 41),                                                                                             ('ENGLISH', 'Thunderstorm. Stay safe.', '천둥번개가 칩니다. 안전에 주의하세요.', 42),                                                                                             ('ENGLISH', 'Heavy thunderstorm. Refrain from going out.', '강한 천둥번개가 칩니다. 외출을 자제하세요.', 43),                                                                                             ('ENGLISH', 'Ragged thunderstorm. Be cautious just in case.', '불규칙한 천둥번개가 칩니다. 혹시 모르니 주의하세요.', 44),                                                                                             ('ENGLISH', 'Thunderstorm with light drizzle. Take an umbrella.', '약한 이슬비가 동반된 천둥번개가 칩니다. 우산을 챙기세요.', 45),                                                                                             ('ENGLISH', 'Thunderstorm with drizzle. Be careful.', '이슬비가 동반된 천둥번개가 칩니다. 주의하세요.', 46),                                                                                             ('ENGLISH', 'Thunderstorm with heavy drizzle. Avoid going out.', '강한 이슬비가 동반된 천둥번개가 칩니다. 외출을 피하세요.', 47),                                                                                             ('ENGLISH', 'Light intensity drizzle. Take an umbrella.', '약한 이슬비가 내립니다. 우산을 챙기세요.', 48),                                                                                             ('ENGLISH', 'Drizzle. Take a light umbrella just in case.', '이슬비가 내립니다. 혹시 모르니 가벼운 우산을 준비하세요.', 49),                                                                                             ('ENGLISH', 'Heavy intensity drizzle. Don’t forget your umbrella when going out.', '강한 이슬비가 내립니다. 외출 시 우산을 잊지 마세요.', 50),                                                                                             ('ENGLISH', 'Light intensity drizzle rain. Prepare an umbrella.', '약한 이슬비가 섞인 비가 내립니다. 우산을 준비하세요.', 51),                                                                                             ('ENGLISH', 'Drizzle rain. Be careful.', '이슬비가 섞인 비가 내립니다. 주의하세요.', 52),                                                                                             ('ENGLISH', 'Heavy intensity drizzle rain. Refrain from going out.', '강한 이슬비가 섞인 비가 내립니다. 외출을 자제하세요.', 53),                                                                                             ('ENGLISH', 'Shower rain and drizzle. Take an umbrella.', '소나기와 이슬비가 섞여 내립니다. 우산을 챙기세요.', 54),                                   ...	Error Code: 1452. Cannot add or update a child row: a foreign key constraint fails (`polingo_db`.`situation_sentence`, CONSTRAINT `FKdi847v12lfod3mtin1clhpww3` FOREIGN KEY (`detailed_situation_id`) REFERENCES `detailed_situation` (`id`))	0.015 sec
