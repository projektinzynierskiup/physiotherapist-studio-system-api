-- Inserty dla tabeli massage
INSERT INTO physioterapist.massage (massage_name, description, appointment_type) VALUES
('Relaksacyjny Masaz', 'Masaz dla relaksu', 'RELAKSACYJNY'),
('Terapeutyczny Masaz', 'Masaz terapeutyczny', 'TERAPEUTYCZNY'),
('Masaz Glowy', 'Masaz dla glowy', 'GŁOWY'),
('Masaz Stop', 'Masaz dla stop', 'STÓP'),
('Terapia Manualna', 'Terapia manualna', 'TERAPIA_MANUALNA');

-- Inserty dla tabeli offer (zakładając, że klucze obce z tabeli massage to kolejno)
-- INSERT INTO physioterapist.offer (offer_name, duration, price, masage_id) VALUES
-- ('Oferta Relaksacyjna', 60, 200, 1),
-- ('Oferta Terapeutyczna', 90, 300, 2),
-- ('Oferta Glow', 30, 150, 3),
-- ('Oferta Stop', 45, 180, 4),
-- ('Oferta Terapia Manualna', 120, 400, 5);
