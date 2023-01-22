--liquibase formatted sql
--changeset <olehkvasha>:<add-question-answer-constraint>
ALTER TABLE question
    ADD CONSTRAINT IF NOT EXISTS fk_question_answer FOREIGN KEY (answer_id) REFERENCES answer (id);

--rollback ALTER TABLE question DROP CONSTRAINT IF EXISTS fk_question_answer;
