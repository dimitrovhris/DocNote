USE docnote;
    INSERT INTO users (first_name, surname, last_name, username, phone, email, address, password, approved)
    VALUES ('Hristiyan', 'Plamenov', 'Dimitrov', 'dimitrovhris', '0885866409','hris.plamenov.dimitrov@gmail.com', 'Stara Zagora, Bulgaria', '064314hrisi', true);

    INSERT INTO users_roles(user_id, role_id)
    VALUES(1, 1);

UPDATE users
SET password = '$2a$10$FxqZq/Sf90ZVGIqbeAM/HOGFn7AAlfnaTj.cJw0mHwFzmjLtFKXS6'
WHERE id = 1;