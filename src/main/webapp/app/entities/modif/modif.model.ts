import dayjs from 'dayjs/esm';

export interface IModif {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  dedated?: dayjs.Dayjs | null;
  denumdp?: number | null;
  decserv?: number | null;
  decoper?: string | null;
  decsean?: string | null;
  numrotat?: number | null;
  matric?: number | null;
  cd1?: number | null;
  decmotif?: number | null;
  heur?: dayjs.Dayjs | null;
  chre?: string | null;
  typ?: string | null;
}

export type NewModif = Omit<IModif, 'id'> & { id: null };
