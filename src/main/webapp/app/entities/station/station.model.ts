export interface IStation {
  id: string;
  decstat?: string | null;
  dectyst?: string | null;
  decrout?: string | null;
  delstat?: string | null;
  delstatfr?: string | null;
  lattitude?: string | null;
  longitude?: string | null;
  valide?: string | null;
}

export type NewStation = Omit<IStation, 'id'> & { id: null };
