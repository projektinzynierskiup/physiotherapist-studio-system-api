-- Inserty dla tabeli massage
INSERT INTO physioterapist.massage (id, massage_name, description, appointment_type) VALUES
(1, 'Relaksacyjny Masaz', 'Masaz dla relaksu', 'RELAKSACYJNY'),
(2,'Terapeutyczny Masaz', 'Masaz terapeutyczny', 'TERAPEUTYCZNY'),
(3,'Masaz Glowy', 'Masaz dla glowy', 'GŁOWY'),
(4,'Masaz Stop', 'Masaz dla stop', 'STÓP'),
(5,'Terapia Manualna', 'Terapia manualna', 'TERAPIA_MANUALNA');

-- Inserty dla tabeli offer (zakładając, że klucze obce z tabeli massage to kolejno)
INSERT INTO physioterapist.offer (id, offer_name, duration, price, masage_id) VALUES
(1,'Oferta Relaksacyjna', 60, 200, 1),
(2,'Oferta Terapeutyczna', 90, 300, 2),
(3,'Oferta Glow', 30, 150, 3),
(4,'Oferta Stop', 45, 180, 4),
(5,'Oferta Terapia Manualna', 120, 400, 5);
