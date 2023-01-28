--liquibase formatted sql
--changeset <olehkvasha>:<add-question-answer-constraint>
ALTER TABLE public.question
    ADD CONSTRAINT fk_question_answer FOREIGN KEY (answer_id) REFERENCES public.answer (id);

--rollback ALTER TABLE question DROP CONSTRAINT IF EXISTS fk_question_answer;
