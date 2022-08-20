-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER cardgame_rebrand_owner
WITH PASSWORD 'cardgame';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO cardgame_rebrand_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO cardgame_rebrand_owner;

CREATE USER cardgame_rebrand_appuser
WITH PASSWORD 'cardgame';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO cardgame_rebrand_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO cardgame_rebrand_appuser;
