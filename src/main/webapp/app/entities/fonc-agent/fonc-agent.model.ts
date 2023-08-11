import dayjs from 'dayjs/esm';

export interface IFoncAgent {
  id: string;
  cdfonc?: string | null;
  matric?: number | null;
  nom?: string | null;
  prenom?: string | null;
  dateff?: dayjs.Dayjs | null;
  valide?: string | null;
}

export type NewFoncAgent = Omit<IFoncAgent, 'id'> & { id: null };
