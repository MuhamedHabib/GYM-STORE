-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 09 mars 2022 à 17:44
-- Version du serveur : 10.4.20-MariaDB
-- Version de PHP : 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `hebergement`
--

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `iddemande` int(255) NOT NULL,
  `nomprenom` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `datedebut` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`iddemande`, `nomprenom`, `contact`, `datedebut`, `image`) VALUES
(1, 'add', 'add', '2022-03-03', 'C:\\\\Users\\\\DELL\\\\Downloads\\\\spring\\\\web_project\\\\SpringBoot4DS2\\\\mahdi\\\\src\\\\resources\\\\ben-affleck.png'),
(2, 'test', 'test', '2022-03-09', 'C:\\\\Users\\\\DELL\\\\Downloads\\\\spring\\\\web_project\\\\SpringBoot4DS2\\\\mahdi\\\\src\\\\resources\\\\ben-affleck.png'),
(3, 'test', '@', '2022-03-10', 'C:\\\\Users\\\\DELL\\\\Downloads\\\\spring\\\\web_project\\\\SpringBoot4DS2\\\\mahdi\\\\src\\\\resources\\\\ben-affleck.png'),
(4, 'test', 'contact', '2022-03-10', 'C:\\\\Users\\\\DELL\\\\Downloads\\\\spring\\\\web_project\\\\SpringBoot4DS2\\\\mahdi\\\\src\\\\resources\\\\ben-affleck.png'),
(5, 'added', 'added', '2022-03-09', 'C:\\\\Users\\\\DELL\\\\Downloads\\\\spring\\\\web_project\\\\SpringBoot4DS2\\\\mahdi\\\\src\\\\resources\\\\ben-affleck.png'),
(6, 'nom prenom', '27234345', '2022-03-09', 'C:\\\\Users\\\\DELL\\\\Downloads\\\\spring\\\\web_project\\\\SpringBoot4DS2\\\\mahdi\\\\src\\\\resources\\\\ben-affleck.png'),
(7, 'ajout', '23234', '2022-03-09', 'C:\\\\Users\\\\DELL\\\\Downloads\\\\spring\\\\web_project\\\\SpringBoot4DS2\\\\mahdi\\\\src\\\\resources\\\\book.png'),
(8, 'aqr', 'aqwc', '2022-03-09', 'C:\\\\Users\\\\DELL\\\\Downloads\\\\spring\\\\web_project\\\\SpringBoot4DS2\\\\mahdi\\\\src\\\\resources\\\\ben-affleck.png'),
(9, 'test', 'test', '2022-03-17', 'C:\\\\Users\\\\DELL\\\\Downloads\\\\spring\\\\web_project\\\\SpringBoot4DS2\\\\mahdi\\\\src\\\\resources\\\\Affiche.png');

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `idoffre` int(11) NOT NULL,
  `titleoffre` varchar(255) NOT NULL,
  `priceoffre` varchar(255) NOT NULL,
  `nombreplace` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `localisation` varchar(255) NOT NULL,
  `style` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `offre`
--

INSERT INTO `offre` (`idoffre`, `titleoffre`, `priceoffre`, `nombreplace`, `description`, `localisation`, `style`, `image`) VALUES
(3, 'titre ', 'haut standing', 'tet', 'type', 'decription', 'tunis', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\mahdi\\src\\resources\\g.jpg'),
(5, 'titre ', 'haut standing', 'tet', 'type', 'decription', 'tunis', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\mahdi\\src\\resources\\book.jpg'),
(10, 'price', 'prcie', 'tet', 'price', 'price', 'price', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\mahdi\\src\\resources\\IBM.jpg'),
(11, 'price', 'prcie', 'tet', 'price', 'price', 'price', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\mahdi\\src\\resources\\IBM.jpg'),
(12, 'price', 'prcie', 'tet', 'price', 'price', 'price', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\mahdi\\src\\resources\\IBM.jpg'),
(14, 'maison', '300 dt', '3', 'isolé', 'Rades', 'haut standing', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\mahdi\\src\\resources\\g.jpg'),
(15, 'maison', '700', '4', 'villa', 'ben arous', 'modern', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\mahdi\\src\\resources\\images.jpg'),
(16, 'maison', '700', '4', 'villa', 'ben arous', 'modern', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\mahdi\\src\\resources\\g.jpg'),
(18, 'price', '4000', 'tet', 'price', 'price', 'price', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\mahdi\\src\\resources\\b.jpg'),
(19, 'price', '400 dt', 'tet', 'price', 'price', 'haut standing', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\mahdi\\src\\resources\\b.jpg'),
(21, 'maison', '300 dt', '3', 'large', 'Rades', 'haut standing', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\mahdi\\src\\resources\\IBM.jpg');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`iddemande`);

--
-- Index pour la table `offre`
--
ALTER TABLE `offre`
  ADD PRIMARY KEY (`idoffre`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `demande`
--
ALTER TABLE `demande`
  MODIFY `iddemande` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `offre`
--
ALTER TABLE `offre`
  MODIFY `idoffre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
