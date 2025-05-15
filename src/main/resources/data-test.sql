-- Insertar Usuario (para psicólogo)
INSERT INTO Usuario (idusuario, nombre, apellido, edad, dni, email, contrasenia)
VALUES (1, 'Luis', 'Salazar', 40, '12345678', 'luis@psicologos.com', 'clave123');

-- Insertar Especialidad (si usas enum puedes saltarlo)

-- Insertar Dirección
INSERT INTO Direccion (iddireccion, nombredireccion, distrito_iddistrito)
VALUES (1, 'Av. Siempre Viva 123', 1);

-- Insertar Psicólogo
INSERT INTO Psicologo (idpsicologo, numcolegiatura, disponibilidad, descripcion, tarifa, usuario_idusuario, especialidad_idespecialidad, direccion_iddireccion)
VALUES (1, 'COL123456', 1, 'Especialista en ansiedad y estrés.', 150.00, 1, 1, 1);

-- Insertar Usuario (para paciente)
INSERT INTO Usuario (idusuario, nombre, apellido, edad, dni, email, contrasenia)
VALUES (2, 'Ana', 'Martínez', 30, '87654321', 'ana@pacientes.com', 'clave456');

-- Insertar Paciente
INSERT INTO Paciente (idpaciente, descripcionpaciente, usuario_idusuario)
VALUES (1, 'Paciente con antecedentes de ansiedad.', 2);

-- Insertar Calificación
INSERT INTO Calificacion (idcalificacion, puntaje, comentario, fecha, psicologo_idpsicologo, paciente_idpaciente)
VALUES (1, 5, 'Muy buen profesional.', '2024-05-10', 1, 1);
