export class GroupRequestDto {
  name: string;
  curatorId: number;

  constructor(name: string, curatorId: number) {
    this.name = name;
    this.curatorId = curatorId;
  }
}
