export interface IMotifa {
  id: string;
  decmotif?: number | null;
  libmotif?: string | null;
}

export type NewMotifa = Omit<IMotifa, 'id'> & { id: null };
