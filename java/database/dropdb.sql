-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'cardgame_rebrand';

DROP DATABASE cardgame_rebrand;

DROP USER cardgame_rebrand_owner;
DROP USER cardgame_rebrand_appuser;
