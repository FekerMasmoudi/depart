export interface IMotifchserv {
  id: string;
  decmotif?: number | null;
  delmotif?: string | null;
  x?: string | null;
  vs?: string | null;
}

export type NewMotifchserv = Omit<IMotifchserv, 'id'> & { id: null };
