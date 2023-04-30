export class CuratorRequestDto {
  name: string;
  lastName: string;
  additionalName: string;
  department: string;
  position: string;

  constructor(
    name: string,
    lastName: string,
    additionalName: string,
    department: string,
    position: string,
    ) {
    this.name = name;
    this.lastName = lastName;
    this.additionalName = additionalName;
    this.department = department;
    this.position = position;
  }
}
