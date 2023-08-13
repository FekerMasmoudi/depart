export interface ITypStat {
  id: string;
  dectyst?: string | null;
  deltyst?: string | null;
}

export type NewTypStat = Omit<ITypStat, 'id'> & { id: null };
