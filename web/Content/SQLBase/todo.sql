-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Авг 13 2018 г., 11:13
-- Версия сервера: 5.5.25
-- Версия PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `todo`
--

-- --------------------------------------------------------

--
-- Структура таблицы `tasks`
--

CREATE TABLE IF NOT EXISTS `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(200) NOT NULL,
  `data` date NOT NULL,
  `idUser` int(11) NOT NULL,
  `flagRecycle` tinyint(1) NOT NULL,
  `flagFix` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `tasks`
--

INSERT INTO `tasks` (`id`, `content`, `data`, `idUser`, `flagRecycle`, `flagFix`) VALUES
(1, 'Do something', '2018-08-11', 2, 0, 0),
(2, 'Join 2 letters', '2018-08-13', 2, 0, 0),
(3, 'Go to market', '2018-08-13', 2, 1, 0),
(4, 'Close main project', '2018-08-14', 2, 0, 0);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `login` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `fname` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `role` varchar(200) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`login`, `password`, `fname`, `email`, `role`, `id`) VALUES
('sys', '111', 'Katrin', 'kate@gmail.com', 'ADMIN', 1),
('boss', '000', 'Dmitry', 'dmitry@gmail.com', 'USER', 2),
('plumb', '123', 'Larisa', 'puperlastik@mail.ru', 'USER', 3),
('flower', '321', 'Olga', 'flower@mail.ru', 'USER', 4),
('javelin', '951', 'Jon', 'jon.j@yandex.com', 'USER', 5);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
