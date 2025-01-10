create table Clinica (
	idClinica int primary key not null auto_increment,
	nome varchar(150),
	email varchar(150),
	senha varchar(8)
);

create table Psicologo (
	idPsicologo int primary key not null auto_increment,
	Nome varchar(150) not null,
	CRP varchar(50) not null
);

create table Paciente (
	idPaciente int primary key not null auto_increment,
	Nome varchar(150) not null,
	CPF varchar(50) not null,
	PsicologoId int not null,
	Convenio varchar(150) not null,
	foreign key (PsicologoId) references Psicologo (idPsicologo)
);

create table Guias (
	idGuia int primary key not null auto_increment,
	NumeroGuia int not null,
	DataPedido Date not null,
	DataLiberacao Date not null,
	TipoSessao varchar(50) not null,
	QuantidadeSessoes int not null,
	Status Boolean,
	DataPrimeiraSessao Date not null,
	PacienteId int not null,
	foreign key (PacienteId) references Paciente (idPaciente)
);

create table Sessoes (
	idSessao int primary key not null auto_increment,
	GuiaId int not null,
	DataSessao Date not null,
	foreign key (GuiaId) references Guias (idGuia)
);

create table Anexo (
    idAnexo int primary key not null auto_increment,
    nomeAnexo varchar(255),
    tipoAnexo varchar(50),
    caminhoAnexo varchar(255),
    guiaId int not null,
    foreign key (guiaId) references Guias (idGuia)
);