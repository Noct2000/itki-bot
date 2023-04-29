import { Curator } from './curator';

export interface Group {
  id: number,
  name: string,
  curator: Curator,
}
