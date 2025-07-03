TRUNCATE TABLE rol RESTART IDENTITY CASCADE;

-- Tabla: rol
INSERT INTO rol (id_rol, name)
VALUES (1, 'PACIENTE'),
       (2, 'PSICOLOGO'),
       (3, 'ADMINISTRADOR');

-- Tabla: usuario
INSERT INTO usuario (id_usuario, id_rol, email, password)
VALUES (1, 3, 'admin@conectamente.com', '$2a$10$D2JXfg1AC.XbPqXhXn0QtO8mDTLnDVu2v9pJWtRIZZItx3b7sxD/i'),
       (2, 1, 'paciente1@example.com', '$2a$10$mE/1b27AwYimjKyR60vlwOPo1WKAzowdvNmSKYtmf./lMNrspQffe'),
       (3, 2, 'psicologo1@example.com', '$2a$10$6feyynGIL/Ljsjb7nJ2k8uxYhu7vF03sBHncc8lAsBQzD9D/4vkWC'),
       (4, 1, 'paciente2@conectamente.com', '$2a$10$YZnV1HKC8tIB7ycMhIaqme3J8JcMk/chluYY/PL1Zhnjld5R.P4/i'),
       (5, 1, 'paciente3@conectamente.com', '$2a$10$KGKYp7gPuGr4VMRBSSYTGukJa1LmQJPLSS.43vLrhmt6VwDi183hW'),
       (6, 2, 'psicologo2@conectamente.com', '$2a$10$4d2dFWlEZMw7fjHM/QI.L.ZWJKSRJiAOf9gbTyj0JVM31AcBf8iwm'),
       (7, 2, 'psicologo3@conectamente.com', '$2a$10$/dnrtHleunSieJ9jd.E6XefYlvBCG.ccbYJgY0p8lCQ8lvXAhvbsC');

-- Tabla: direccion
INSERT INTO direccion (id_direccion, departamento, distrito, nombre_direccion)
VALUES (1, 'LIMA', 'MIRAFLORES', 'Av. Larco 123'),
       (2, 'LIMA', 'SAN_ISIDRO', 'Calle Los Cedros 456'),
       (3, 'LIMA', 'SURQUILLO', 'Jr. Ayacucho 789');

-- Tabla: paciente
INSERT INTO paciente (
    id_paciente, edad, id_direccion, usuario_id_usuario,
    created_at,            updated_at,           apellido,
    descripcion_paciente,  dni,       nombre
)
VALUES
    (1, 25, 1, 2,
     '2025-06-12 08:00:00', '2025-06-13 09:30:00', 'Pérez',
     'Paciente con ansiedad',                   '12345678', 'Ana'),

    (4, 22, 1, 4,
     '2025-06-14 10:15:00', '2025-06-14 10:15:00', 'López',
     'Paciente de prueba 2',                    '23456789', 'Pedro'),

    (5, 29, 2, 5,
     '2025-06-14 11:00:00', '2025-06-14 11:00:00', 'Martínez',
     'Paciente de prueba 3',                    '34567890', 'Laura');

-- Tabla: psicologo
INSERT INTO psicologo (
    id_psicologo, edad, id_direccion, tarifa, usuario_id_usuario,
    created_at,             updated_at,             apellido,
    descripcion_psicologo,  disponibilidad,         dni,
    especialidad,           nombre,                 numero_colegiatura
)
VALUES
    (1, 35, 1, 150.0, 3,
     '2025-06-12 08:05:00', '2025-06-13 09:35:00', 'Ramírez',
     'Especialista en ansiedad', 'LUNES-VIERNES', '99887766',
     'CLINICA',               'Carlos',               'CP123'),

    (4, 33, 3, 120.0, 6,
     '2025-06-14 10:20:00', '2025-06-14 10:20:00', 'Gómez',
     'Psicólogo prueba 2',      'MARTES-JUEVES',        '55667777',
     'SOCIAL',                'Daniel',               'CP004'),

    (5, 37, 1, 140.0, 7,
     '2025-06-14 11:05:00', '2025-06-14 11:05:00', 'Díaz',
     'Psicólogo prueba 3',      'MIÉRCOLES-VIERNES',    '66778899',
     'DEPORTIVA',             'Elena',                'CP005');

-- Tabla: cita
INSERT INTO cita (
    id_cita, fecha_cita,   hora_cita, id_paciente, id_psicologo,
    fecha,                  descripcion,        estado_cita
)
VALUES
    (1, '2025-06-12', '10:00:00', 1, 1, '2025-06-12 08:00:00', 'Primera sesión',    'PENDIENTE'),
    (2, '2025-06-13', '11:00:00', 4, 1, '2025-06-13 09:30:00', 'Seguimiento de Ana', 'COMPLETADA'),
    (3, '2025-06-14', '12:00:00', 5, 5, '2025-06-14 11:00:00', 'Evaluación Laura',   'CANCELADA'),
    (4, '2025-06-15', '09:30:00', 4, 4, '2025-06-15 08:45:00', 'Terapia insomnio',   'PENDIENTE'),
    (5, '2025-06-16', '14:00:00', 5, 5, '2025-06-16 13:50:00', 'Estrés laboral',     'COMPLETADA');

-- Tabla: conversaciones
INSERT INTO conversaciones ( id_paciente, id_psicologo, fecha_creacion)
VALUES
    ( 1, 1, '2025-06-12 08:00:00'),
    ( 4, 1, '2025-06-14 10:15:00'),
    ( 5, 5, '2025-06-14 11:05:00'),
    ( 4, 4, '2025-06-15 08:45:00'),
    ( 5, 4, '2025-06-16 13:50:00');
-- Tabla: mensaje
INSERT INTO mensaje (id_mensaje, id_conversacion, id_usuario, fecha_envio, contenido)
VALUES
    (1, 1, 1, '2025-06-12 08:05:00', 'Hola, necesito ayuda.'),
    (2, 2, 2, '2025-06-13 09:35:00', 'Claro, cuéntame más.'),
    (3, 3, 3, '2025-06-14 11:05:00', 'Por favor coordinen vía la plataforma.'),
    (4, 4, 4, '2025-06-15 08:50:00', 'Tengo problemas para dormir.'),
    (5, 4, 4, '2025-06-15 08:55:00', '¿Podría darme consejos?'),
    (6, 5, 5, '2025-06-16 13:55:00', 'Estoy estresada en el trabajo.');

-- Tabla: calificacion
INSERT INTO calificacion (id_calificacion, id_paciente, id_psicologo, puntaje, fecha, comentario)
VALUES
    (1, 1, 1, 5, '2025-06-12 08:10:00', 'Excelente atención.'),
    (2, 4, 4, 4, '2025-06-15 09:00:00', 'Muy útil para el insomnio.'),
    (3, 5, 5, 3, '2025-06-16 14:10:00', 'Buena experiencia.'),
    (4, 4, 1, 5, '2025-06-15 09:30:00', 'Me ayudó muchísimo.'),
    (5, 5, 4, 4, '2025-06-16 14:20:00', 'Enfoque profesional.');

-- Psicologo
SELECT setval(
               pg_get_serial_sequence('public.psicologo', 'id_psicologo'),
               (SELECT COALESCE(MAX(id_psicologo), 1) FROM psicologo)
       );

-- Paciente
SELECT setval(
               pg_get_serial_sequence('public.paciente', 'id_paciente'),
               (SELECT COALESCE(MAX(id_paciente), 1) FROM paciente)
       );

-- Usuario
SELECT setval(
               pg_get_serial_sequence('public.usuario', 'id_usuario'),
               (SELECT COALESCE(MAX(id_usuario), 1) FROM usuario)
       );

-- Calificación
SELECT setval(
               pg_get_serial_sequence('public.calificacion', 'id_calificacion'),
               (SELECT COALESCE(MAX(id_calificacion), 1) FROM calificacion)
       );

-- Mensaje
SELECT setval(
               pg_get_serial_sequence('public.mensaje', 'id_mensaje'),
               (SELECT COALESCE(MAX(id_mensaje), 1) FROM mensaje)
       );

-- Conversación
SELECT setval(
               pg_get_serial_sequence('public.conversaciones', 'id_conversacion'),
               (SELECT COALESCE(MAX(id_conversacion), 1) FROM conversaciones)
       );

-- Cita
SELECT setval(
               pg_get_serial_sequence('public.cita', 'id_cita'),
               (SELECT COALESCE(MAX(id_cita), 1) FROM cita)
       );

-- Certificado
--SELECT setval(
--              pg_get_serial_sequence('public.certificado', 'id_certificate_file'),
--               (SELECT COALESCE(MAX(id_certificate_file), 1) FROM certificado)
--       );

-- Dirección
SELECT setval(
               pg_get_serial_sequence('public.direccion', 'id_direccion'),
               (SELECT COALESCE(MAX(id_direccion), 1) FROM direccion)
       );
