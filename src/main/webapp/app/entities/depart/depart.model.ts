import dayjs, { Dayjs } from 'dayjs/esm';

export interface IDepart {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  decserv?: number | null;
  decoper?: string | null;
  decsean?: string | null;
  dedated?: dayjs.Dayjs | null;
  denumdp?: number | null;
  matric?: number | null;
  matric1?: number | null;
  cdmac?: number | null;
  deheups?: dayjs.Dayjs | null;
  deheufs?: dayjs.Dayjs | null;
  denbrro?: number | null;
  deheuaa?: dayjs.Dayjs | null;
  deheudr?: dayjs.Dayjs | null;
  deheupd?: dayjs.Dayjs | null;
  deampli?: dayjs.Dayjs | null;
  obsind?: string | null;
  vldroul?: string | null;
  deetat?: string | null;
  deannul?: string | null;
  decclot?: string | null;
  execute?: string | null;
  motif_a?: string | null;
  observ?: string | null;
  recettes?: number | null;
  nbrevoy?: number | null;
  decmotifch?: number | null;
  decmotifre?: number | null;
  cd1?: number | null;
  cd2?: number | null;
  cd3?: number | null;
  decmotifcha?: number | null;
  decmotifrea?: number | null;
}

export type NewDepart = Omit<IDepart, 'id'> & { id: null };
