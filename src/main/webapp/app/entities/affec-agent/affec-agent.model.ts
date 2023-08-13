export interface IAffecAgent {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  decserv?: number | null;
  decoper?: string | null;
  decsean?: string | null;
  cdsocie?: string | null;
  decexer?: number | null;
  cdmois?: number | null;
  matric?: number | null;
  matric2?: number | null;
  cdmac?: string | null;
}

export type NewAffecAgent = Omit<IAffecAgent, 'id'> & { id: null };
