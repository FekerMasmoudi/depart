export interface IAgence {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  delagenc?: string | null;
  defaultagenc?: string | null;
}

export type NewAgence = Omit<IAgence, 'id'> & { id: null };
