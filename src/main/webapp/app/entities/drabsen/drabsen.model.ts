import dayjs from 'dayjs/esm';

export interface IDrabsen {
  id: string;
  cdtypab?: string | null;
  matric?: number | null;
  databs?: dayjs.Dayjs | null;
  numabs?: number | null;
  nbrabs?: number | null;
  validabs?: number | null;
  observaabs?: string | null;
  cd1?: number | null;
  cd2?: number | null;
  cd3?: number | null;
}

export type NewDrabsen = Omit<IDrabsen, 'id'> & { id: null };
