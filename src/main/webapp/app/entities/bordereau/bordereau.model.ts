import dayjs from 'dayjs/esm';

export interface IBordereau {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  exercice?: number | null;
  denbord?: number | null;
  det_deccent?: number | null;
  det_decagenc?: number | null;
  decserv?: number | null;
  cdmac?: string | null;
  decoper?: string | null;
  decsean?: string | null;
  dedated?: dayjs.Dayjs | null;
  denumdp?: number | null;
  matric?: number | null;
  matric1?: number | null;
  natbord?: string | null;
  detadedb?: dayjs.Dayjs | null;
  numb_pdat?: number | null;
  deheupsr?: dayjs.Dayjs | null;
  demnttn?: number | null;
  demntto?: number | null;
  decetbr?: string | null;
  decetcs?: string | null;
  denumcs?: number | null;
  denumbr?: number | null;
  denumver?: number | null;
  declote?: string | null;
  date_saisie?: dayjs.Dayjs | null;
  clorec?: string | null;
  demntvers?: number | null;
}

export type NewBordereau = Omit<IBordereau, 'id'> & { id: null };
