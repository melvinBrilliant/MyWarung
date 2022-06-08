use master

go
create database MyWarung
use MyWarung
go

create table Kategori (
	KategoriID int not null identity(1,1),
	NamaKategori varchar(50) not null,
	Deskripsi varchar(200) null,
	primary key (KategoriID)
)

create table Produk (
	ProdukID int not null identity(1,1),
	NamaProduk varchar(50) not null,
	KategoriID int not null references dbo.Kategori(KategoriID),
	HargaPerUnit money not null,
	JumlahStok int not null
	primary key (ProdukID)
)

create table Belanja (
	BelanjaID int not null identity(1,1),
	[Status] varchar(50) not null,
	primary key (BelanjaID)
)

create table DetailBelanja (
	BelanjaID int not null references dbo.Belanja(BelanjaID),
	ProdukID int not null references dbo.Produk(ProdukID),
	JumlahBarang int not null,
	HargaPerUnit money not null,
	primary key (BelanjaID, ProdukID),
)

