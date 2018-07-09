-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 09/07/2018 às 16:19
-- Versão do servidor: 10.1.30-MariaDB
-- Versão do PHP: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `doonfe`
--

--
-- Fazendo dump de dados para tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(18);

--
-- Fazendo dump de dados para tabela `Itens`
--

INSERT INTO `Itens` (`id`, `codigo`, `descricao`, `preco`, `quantidade`, `notaFiscal_id`) VALUES
(1, 1, 'Primeiro Item', 10, 10, 1),
(2, 2, 'Segundo Item', 5, 2, 1),
(3, 1, 'Produto1', 50, 10, 2),
(4, 2, 'Produto2', 40, 5, 2),
(5, 5, 'Batata', 15, 100, 3),
(15, 3, 'Produto3', 25, 5, 2),
(16, 1, 'Bicicleta', 300, 1, 4),
(17, 2, 'Placa de Video', 1500, 1, 4),
(18, 3, 'Notebook', 20000, 2, 4),
(19, 4, 'Guarda-Roupas', 800, 1, 4),
(20, 5, 'Telefone', 1500, 3, 4),
(21, 6, 'Televisao', 3000, 1, 4),
(22, 7, 'Playstation', 2300, 2, 4),
(23, 8, 'XONE', 2000, 2, 4),
(24, 9, 'Monitor', 500, 4, 4),
(25, 10, 'Computador', 3000, 1, 4),
(26, 11, 'Webcam', 250, 1, 4);

--
-- Fazendo dump de dados para tabela `NotaFiscal`
--

INSERT INTO `NotaFiscal` (`id`, `dataEmissao`, `dataOperacao`, `informacoesComplementares`, `modelo`, `natureza`, `numeroNota`, `destinatario_id`, `emitente_id`) VALUES
(1, '2018-07-02 00:00:00', '2018-07-01 00:00:00', 'NA', 'MODELO1_A', 'VENDA', 10, 3, 7),
(2, '2018-08-09 00:00:00', '2018-08-09 00:00:00', 'Nenhuma', 'MODELO1_A', 'VENDA', 11, 5, 6),
(3, '2018-05-17 00:00:00', '2018-05-17 00:00:00', 'Complemento', 'MODELO1_A', 'VENDA', 15, 8, 9),
(4, '2018-07-09 00:00:00', '2018-07-09 00:00:00', 'Sem informaçoes', 'MODELO1_A', 'VENDA', 12, 16, 17);

--
-- Fazendo dump de dados para tabela `PessoaFisica`
--

INSERT INTO `PessoaFisica` (`id`, `estado`, `cpf`, `nome`) VALUES
(4, 'MG', '1111111111', 'Ramon'),
(5, 'RJ', '2222222222', 'Nilton'),
(6, 'MG', '1111111111', 'Ramon'),
(8, 'RJ', '22222222222', 'Usuario'),
(16, 'MG', '4444444444', 'OLX2'),
(17, 'MG', '8888888888', 'OLX');

--
-- Fazendo dump de dados para tabela `PessoaJuridica`
--

INSERT INTO `PessoaJuridica` (`id`, `estado`, `cnpj`, `inscricaoEstadual`, `razaoSocial`) VALUES
(3, 'SP', '222222222', '999999999', 'Empresa'),
(7, 'MG', '1111111111', '54555555555', 'Ramon'),
(9, 'SP', '1111111111', '147852369', 'ABC');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
