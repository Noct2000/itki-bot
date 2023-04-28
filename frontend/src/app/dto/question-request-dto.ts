export class QuestionRequestDto {
  questionText: string;
  answerText: string;

  constructor(
    questionText: string,
    answerText: string,
    ) {
    this.questionText = questionText;
    this.answerText = answerText;
  }
}
