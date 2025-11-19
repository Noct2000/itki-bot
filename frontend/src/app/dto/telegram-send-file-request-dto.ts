export class TelegramSendFileRequestDto {
  caption: string;
  filenames: Array<string>;

  constructor(
    caption: string,
    filenames: Array<string>,
  ) {
    this.caption = caption;
    this.filenames = filenames;
  }
}
