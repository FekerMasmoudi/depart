export interface IDrtypab {
  id: string;
  cdtypab?: string | null;
  lbtypab?: string | null;
  dabsjt?: string | null;
  dabsjp?: string | null;
}

export type NewDrtypab = Omit<IDrtypab, 'id'> & { id: null };
