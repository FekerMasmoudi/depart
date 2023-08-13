export interface IPeriode {
  id: string;
  decoper?: string | null;
  denoper?: string | null;
  primaire?: string | null;
  startdate?: string | null;
  enddate?: string | null;
}

export type NewPeriode = Omit<IPeriode, 'id'> & { id: null };
