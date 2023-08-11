import dayjs from 'dayjs/esm';

export interface IRotRserv {
  deccent?: number | null;
  decagenc?: number | null;
  dedated?: dayjs.Dayjs | null;
  matric?: number | null;
  heurdeb?: dayjs.Dayjs | null;
  heurfin?: dayjs.Dayjs | null;
  statut?: string | null;
  lieedeb?: dayjs.Dayjs | null;
  lieefin?: dayjs.Dayjs | null;
  program?: string | null;
  cd1?: number | null;
  cd2?: number | null;
  cd3?: number | null;
  id: string;
  annul?: string | null;
}

export type NewRotRserv = Omit<IRotRserv, 'id'> & { id: null };
