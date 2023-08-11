export interface IGroupe {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  codgrp?: number | null;
  libgrp?: string | null;
  dectyli?: string | null;
  libgrpfr?: string | null;
}

export type NewGroupe = Omit<IGroupe, 'id'> & { id: null };
