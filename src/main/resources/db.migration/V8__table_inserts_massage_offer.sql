-- Inserty dla tabeli massage
INSERT INTO pchysioterapist.massage (massage_name, description, appointment_type) VALUES
('Relaksacyjny Masaz', 'Masaz dla relaksu', 'RELAKSACYJNY'),
('Terapeutyczny Masaz', 'Masaz terapeutyczny', 'TERAPEUTYCZNY'),
('Masaz Glowy', 'Masaz dla glowy', 'GŁOWY'),
('Masaz Stop', 'Masaz dla stop', 'STÓP'),
('Terapia Manualna', 'Terapia manualna', 'TERAPIA_MANUALNA');

-- Inserty dla tabeli offer (zakładając, że klucze obce z tabeli massage to kolejno 1,2,3,4,5)
INSERT INTO pchysioterapist.offer (offer_name, duration, price, masage_id) VALUES
('Oferta Relaksacyjna', 60, 200, 1),
('Oferta Terapeutyczna', 90, 300, 2),
('Oferta Glow', 30, 150, 3),
('Oferta Stop', 45, 180, 4),
('Oferta Terapia Manualna', 120, 400, 5);
