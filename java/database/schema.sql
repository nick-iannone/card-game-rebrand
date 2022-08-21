BEGIN TRANSACTION;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS cards CASCADE;
DROP TABLE IF EXISTS decks CASCADE;
DROP TABLE IF EXISTS decks_cards CASCADE;
DROP TABLE IF EXISTS decks_contracts CASCADE;
DROP TABLE IF EXISTS contracts CASCADE;
DROP TABLE IF EXISTS histories CASCADE;
DROP TABLE IF EXISTS threads CASCADE;
DROP TABLE IF EXISTS threads_histories CASCADE;
DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS threads_messages CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_card_id;
DROP SEQUENCE IF EXISTS seq_deck_id;
DROP SEQUENCE IF EXISTS seq_cont_id;
DROP SEQUENCE IF EXISTS seq_history_id;
DROP SEQUENCE IF EXISTS seq_thread_id;
DROP SEQUENCE IF EXISTS seq_message_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_card_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_deck_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_cont_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_history_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_thread_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_message_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_game_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
        status varchar(20) DEFAULT 'offline' NOT NULL,
        session_id varchar(8),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE histories (
        history_id int DEFAULT nextval('seq_history_id'::regClass) NOT NULL,
        user_id int NOT NULL,
        CONSTRAINT PK_history PRIMARY KEY (history_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id) 
);

CREATE TABLE threads (
        thread_id int DEFAULT nextval('seq_thread_id'::regClass) NOT NULL,
        owner_username varchar(20) NOT NULL,
        chat_with varchar(20) NOT NULL,
        CONSTRAINT PK_thread PRIMARY KEY (thread_id)
);

CREATE TABLE messages (
        message_id int DEFAULT nextval('seq_message_id'::regClass) NOT NULL,
        from_username varchar(20),
        to_username varchar(20),
        content varchar(1000),
        timestamp varchar(50),
        is_read boolean DEFAULT false,
        CONSTRAINT PK_message PRIMARY KEY (message_id)
);

CREATE TABLE threads_histories (
        history_id int,
        thread_id int,
        CONSTRAINT fk_history FOREIGN KEY (history_id) REFERENCES histories(history_id),
        CONSTRAINT fk_thread FOREIGN KEY (thread_id) REFERENCES threads(thread_id)
        
);

CREATE TABLE threads_messages (
        thread_id int,
        message_id int,
        CONSTRAINT fk_thread FOREIGN KEY (thread_id) REFERENCES threads(thread_id),
        CONSTRAINT fk_message FOREIGN KEY (message_id) REFERENCES messages(message_id)
);

CREATE TABLE decks (
        deck_id int DEFAULT nextval('seq_deck_id'::regClass)NOT NULL,
        owner_id int,
        name varchar(50),
        image_name varchar(100),
        is_favorite boolean DEFAULT false,
        CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES users(user_id),
        CONSTRAINT PK_deck PRIMARY KEY (deck_id)
);

CREATE TABLE contracts (
	id int DEFAULT nextval('seq_cont_id'::regclass)NOT NULL,
	name varchar(200) NOT NULL,
	cost varchar(4) NOT NULL,
	text varchar(300) NOT NULL,
	duration int,
	age int DEFAULT 0,
	is_active boolean,
        has_counters boolean DEFAULT FALSE,
        count int DEFAULT 1,
	CONSTRAINT PK_contracts PRIMARY KEY (id)
);

INSERT INTO contracts (name, cost, text, duration, age, is_active, has_counters) 
VALUES ('Randomoized Drone Strikes', '10', 'At the beginning of your turn put a strike counter on this card. When there are 2 strike counters on this card, remove them and destroy a random enemy troop.', 4, 0, false, true);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Enlist the Masses', '10', 'At the beginning of your turn, create an Enlisted Civilian.', 3, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Misinformation Campaign', '15', E'At the beginning of your turn, reduce your opponent\'s Stability by 2.', 3, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active)
VALUES ('Repair Services', '5', 'At the beginning of your turn, restore 5 Integrity to your HQ.', 2, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active, has_counters) 
VALUES ('Morale Boost', '15', 'At the beginning of your turn, put a morale counter on this card. Your troops gain +1 to all attributes for each morale counter on this card.', 2, 0, false, true);
INSERT INTO contracts (name, cost, text, duration, age, is_active, has_counters) 
VALUES ('Short-Term Loan', '10', 'At the beginning of your turn put an interest counter on this card, then you gain 5 Credits for each interest counter.', 2, 0, false, true);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Micro-Viruses', '5', 'At the beginning of your turn, this deals 1 damage to an enemy target chosen randomly (Troop, HQ, or Network).', 4, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Firewall Update', '5', 'At the beginning of your turn, increase your Stability by 1.', 3, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Data Mine', '10', 'At the beginning of your turn, draw a card.', 2, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active, has_counters) 
VALUES ('Major Breakthrough', '20', 'At the beginning of your turn, put a research counter on this, then draw a card and gain 5 Credits for each research counter.', 2, 0, false, true);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Bounty', '10', 'At the beginning of your turn, destroy an enemy troop.', 1, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Net Recruitment Program', '20', 'At the beginning of your turn, put a random troop from your hand into play.', 2, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Stimpak Requisition', '5', 'At the beginning of your turn, give a random friendly troop +2 resilience.', 3, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Combat Instruction', '5', 'At the beginning of your turn, give a random friendly troop +2 attack.', 3, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Call in the Ringer', '20', 'At the beginning of your turn, draw a Fabled or Feared troop from your deck.', 1, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('I Know A Guy', '10', 'At the beginning of your turn, draw a Heroic or Vile troop from your deck.', 1, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active, has_counters) 
VALUES ('Spread-Missle Attack', '20', 'At the beginning of your turn put a missile counter on this, then deal 2 damage to enemy troops for each missile counter.', 2, 0, false, true);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Ceasefire', '30', E'At the beginning of your turn, return all troops to their owners\' hands and increase each player\'s Stability by 2.', 1, 0, false);
INSERT INTO contracts (name, cost, text, duration, age, is_active) 
VALUES ('Stimulus Package', '10', 'At the beginning of your turn, put an aid counter on this. At 1 counter create a 2/2/2 Soldier. At 2, gain 10 gold. At 3, restore 5 integrity and gain 2 reputation.', 3, 0, true);

CREATE TABLE decks_contracts (
        deck_id int,
        contract_id int,
        CONSTRAINT fk_deck FOREIGN KEY (deck_id) REFERENCES decks(deck_id),
        CONSTRAINT fk_contract FOREIGN KEY (contract_id) REFERENCES contracts(id)
);

CREATE TABLE cards (
	id int DEFAULT nextval('seq_card_id'::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	cost varchar(4) NOT NULL,
	image varchar(200) NOT NULL,
	abilities varchar(200),
	flavor varchar(200),
	attack int,
	resilience int,
	loyalty int,
	is_attacking boolean DEFAULT false,
	is_target boolean DEFAULT false,
	can_attack boolean DEFAULT false,
        on_enter boolean DEFAULT false,
        on_leave boolean DEFAULT false,
        on_attack boolean DEFAULT false,
        on_damage boolean DEFAULT false,
        on_turn_start boolean DEFAULT false,
        on_turn_end boolean DEFAULT false,
        is_heroic boolean DEFAULT false,
        is_fabled boolean DEFAULT false,
        is_hallowed boolean DEFAULT false,
        is_vile boolean DEFAULT false,
        is_feared boolean DEFAULT false,
        is_dreaded boolean DEFAULT false,
        count int DEFAULT 1,

	CONSTRAINT PK_cards PRIMARY KEY (id)
);

INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter) 
VALUES ('Enlisted Civilian', '5', 'enlisted_civilian.jpg', 'When this or another Enlisted Civilian enters, if there is another troop in the combat zone, this gains +1 Resilience.', 1, 2, 4, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, on_leave, is_heroic) 
VALUES ('Field Commander', '15', 'commander.jpg', 'All friendly troops have +1 Attack.', 3, 4, 6, true, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, can_attack) 
VALUES ('Hired Gun', '5', 'assault_tech.jpg', 'Readiness', 4, 1, 1, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, is_feared)
VALUES ('Nikita of the Old Ways', '15', 'nikita.jpg', 'When this enters, it deals its Attack to an enemy troop.', 5, 3, 5, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, can_attack, on_enter)
VALUES ('Scout', '5', 'scout.jpg', 'Readiness. When this enters, draw a card.', 1, 1, 2, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter)
VALUES ('Strike Squad', '20', 'holy_shit.jpg', 'When this enters, create two Enlisted Civilians.', 3, 3, 3, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, is_heroic)
VALUES ('Analyst', '20', 'analyst.jpg', 'When this enters, draw a card, draw a Subroutine, gain 5 Credits, restore 5 Integrity and gain 1 Stability.', 3, 3, 4, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter)
VALUES ('Brothers at Arms', '10', 'baa.jpg', 'When this enters, copy it.', 2, 2, 3, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, is_hallowed)
VALUES ('Xuun, Dread Fleet Commander', '40', 'dread_commander.jpg', 'When this enters, create 1 Field Commander, 1 Brute Bot and 1 Enlisted Civilian.', 5, 6, 10, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, can_attack, on_enter)
VALUES ('Delta Trooper', '10', 'empowered.jpg', 'Readiness. When this enters, destroy an enemy troop with 3 or less resilience.', 1, 2, 2, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, is_vile)
VALUES ('Single-Target Specialist', '20', 'assassin.jpg', 'When this enters, destroy an enemy troop.', 3, 1, 2, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, can_attack, on_enter, on_leave, on_attack, is_fabled)
VALUES ('Merek, Renegade Icon', '15', 'space_pirate.jpg', 'Readiness. When this enters, attacks, or dies, gain 5 coins.', 1, 4, 3, true, true, true, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter)
VALUES ('Brute Bot', '15', 'dread_leader.jpg', E'When this enters, reduce an enemy troop\'s attack, resilience, and loyalty by 1.', 4, 4, 2, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter)
VALUES ('Mainframe Accessor Bot', '15', 'directive.jpg', 'When this enters, draw two cards.', 1, 1, 3, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter)
VALUES ('Hype Man', '5', 'hype_man.jpg', 'When this enters, it readies a friendly troop.', 2, 1, 4, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, can_attack, on_enter, on_attack, is_dreaded)
VALUES ('Vance, Infiltrator Supreme', '15', 'vance.jpg', 'Readiness. When this enters or attacks, reduce opposing Stability by 2.', 3, 4, 10, true, true, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, on_leave, on_attack, is_hallowed)
VALUES ('AXA-99, Network Defense', '15', 'data_core.jpg', 'When this enters, attacks, or dies, increase your Stability by 1. When it dies, decrease opposing Stability by 1.', 2, 6, 10, true, true, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, can_attack, on_enter)
VALUES ('Reinforcements', '10', 'reinforcements.jpg', 'Readiness. When this enters, return a friendly troop to your hand.', 2, 1, 3, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, is_heroic)
VALUES ('Enforcer', '15', 'enforcer.jpg', 'When this enters, it deals its Attack to an enemy troop. The enemy troop deals its Attack to this.', 4, 4, 4, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, is_dreaded)
VALUES ('Titus, Battle-Ender', '40', 'titus.jpg', 'When this enters, destroy all other troops.', 8, 8, 10, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, is_feared)
VALUES ('Taan, All-Seeing Splicer', '20', 'axa_99.jpg', 'When this enters, choose one: Destroy an enemy troop, create 2 Thralls, or reduce opposing Stability by 3.', 5, 5, 10, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_turn_start)
VALUES ('Structural Repair Unit', '5', 'repair_bots.jpg', 'At the beginning of your turn, restore 5 Integrity.', 1, 3, 4, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, is_heroic)
VALUES ('Data Extractors', '10', 'data_breach.jpg', 'When this enters, choose one: create an Informant, gain 15 Credits, or deal 1 damage to all enemy troops.', 1, 2, 6, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, on_leave, on_damage, is_vile)
VALUES ('Mother of Thralls', '15', 'splicer_queen.jpg', 'When this enters create 2 Thralls. When this takes damage, give all friendly Thralls +1 Attack.', 1, 3, 5, true, true, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_turn_start)
VALUES ('Self-Replicating Bot', '5', 'self_replication_2.jpg', 'At the beginning of your turn, create a copy of this.', 1, 2, 5, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, is_fabled)
VALUES ('Thessa, the Helping Hand', '10', 'thessa.jpg', 'When this enters, give a friendly troop +4 Attack', 2, 3, 10, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, can_attack, on_enter)
VALUES ('Informant', '5', 'spotted.jpg', E'Readiness. This troop\'s Attack can\'t be greater than 1 and it can only attack Stability.', 1, 3, 1, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter)
VALUES ('Hacker', '5', 'hacker.jpg', 'When this enters, reduce opposing Stability by 2.', 1, 3, 1, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter)
VALUES ('Embedded Assault Unit', '10', 'sensory.jpg', 'When this enters, start a random Subroutine from your deck.', 2, 2, 3, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, on_damage, is_heroic)
VALUES ('Kinetic Conversion System', '15', 'emergency_repairs.jpg', 'When this takes damage, restore 2 Integrity.', 1, 8, 10, true, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, on_turn_start, is_feared)
VALUES ('x3m0, the Entwined', '10', 'x3m0.jpg', 'When this enters, gain 2 Stability. At the beginning of your turn, gain 5 Credits and 1 Stability. This can only attack Stability.', 1, 5, 3, true, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter, on_leave, on_attack, is_dreaded)
VALUES ('Wrex', '30', 'wrex.jpg', 'When this enters, attacks, or dies, give all friendly troops +1 to all stats.', 3, 7, 10, true, true, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_turn_start)
VALUES ('Swarm Bot', '5', 'swarm_bot.jpg', E'At the beginning of your turn, give +1 Attack and +1 Resilience to all friendly troops named \"Swarm Bot\", then create a Swarm Bot.', 1, 1, 1, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_enter)
VALUES ('Provisioner Bot', '15', 'rover.jpg', 'When this enters, give a friendly troop +2 Attack and +2 Resilience.', 2, 3, 3, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, can_attack, on_enter, is_feared)
VALUES ('Assimilator Hybrid', '15', 'replacement.jpg', 'Readiness. When this enters, it becomes a copy of an enemy troop.', 1, 1, 1, true, true, true);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, on_attack, is_heroic) 
VALUES ('BB-90, Bunker Buster', '20', 'fat_mech.jpg','When this attacks the HQ, it deals its damage to the HQ twice.', 5, 5, 10, true, true);
INSERT INTO cards (name, cost, image, attack, resilience, loyalty) 
VALUES ('Human', '0', 'technician.jpg', 1, 1, 1);
INSERT INTO cards (name, cost, image, attack, resilience, loyalty) 
VALUES ('Robot', '0', 'robot.jpg', 1, 1, 1);
INSERT INTO cards (name, cost, image, attack, resilience, loyalty) 
VALUES ('Hybrid', '0', 'hybrid.jpg', 1, 1, 1);
INSERT INTO cards (name, cost, image, attack, resilience, loyalty) 
VALUES ('Deepened', '0', 'deepened.jpg', 1, 1, 1);
INSERT INTO cards (name, cost, image, attack, resilience, loyalty) 
VALUES ('Unmanaged Organic', '0', 'unmanaged_organic.jpg', 1, 1, 1);
INSERT INTO cards (name, cost, image, abilities, attack, resilience, loyalty, can_attack, on_attack, is_hallowed) 
VALUES ('HVTE, the Crowd Pleaser', '20', 'tank_3.jpg', 'Readiness. Whenever this troop attacks, it deals one damage to each enemy troop.', 4, 6, 5, true, true, true);



CREATE TABLE decks_cards (
        deck_id int,
        card_id int,
        CONSTRAINT fk_deck FOREIGN KEY (deck_id) REFERENCES decks(deck_id),
        CONSTRAINT fk_card FOREIGN KEY (card_id) REFERENCES cards(id)
);


CREATE TABLE games (
        game_id int DEFAULT nextval('seq_game_id'::regClass)NOT NULL,
        p1_id int,
        p2_id int,
        active_player int,
        is_active boolean,
        winner varchar(50),
        loser varchar(50),
        timestamp varchar(50),

        CONSTRAINT PK_games PRIMARY KEY (game_id)
);

CREATE TABLE public_game_states (
        game_id int,
        user_id int,
        username varchar(50),
        lastEvent varchar(300),
        ready_for_battle boolean,
        concede boolean,
        roll_phase boolean,
        roll_for_first int,
        goes_first boolean,
        mulligan_phase boolean,
        keep_hand boolean,
        keep_contracts boolean,
        turn_count int,
        is_turn boolean,
        turn_begin boolean,
        turn_end boolean,
        target_initiator varchar(100),
        kill int,
        fort int,
        rep int,
        cache int,
        directive varchar(100),
        attacker_id int,
        target_id int,
        attack_value int,
        attack_type varchar(20),

        CONSTRAINT PK_states PRIMARY KEY (user_id),
        CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(game_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE cz_cards (
        card_id int,
        game_id int,
        user_id int,

        CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(game_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE cz_mods (
        mod varchar(100),
        game_id int,
        user_id int,

        CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(game_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE cont_act (
        cont_id int,
        game_id int,
        user_id int,

        CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(game_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE cont_comp (
        cont_id int,
        game_id int,
        user_id int,

        CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(game_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE cont_mods (
        mod varchar(100),
        game_id int,
        user_id int,

        CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(game_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE hand_cards (
        card_id int,
        game_id int,
        user_id int,

        CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(game_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE deck_cards_game (
        card_id int,
        game_id int,
        user_id int,

        CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(game_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE cont_avail (
        cont_id int,
        game_id int,
        user_id int,

        CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(game_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE cont_deck (
        cont_id int,
        game_id int,
        user_id int,

        CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(game_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE dead_zone (
        card_id int,
        game_id int,
        user_id int,

        CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(game_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

COMMIT TRANSACTION;