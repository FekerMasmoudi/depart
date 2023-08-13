export interface IAffectagent {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  decserv?: number | null;
  decoper?: string | null;
  decsean?: string | null;
  cdmois?: number | null;
  cdsocie?: string | null;
  decexer?: number | null;
  matric?: number | null;
  matric2?: number | null;
  cdmac?: string | null;
}

export type NewAffectagent = Omit<IAffectagent, 'id'> & { id: null };
